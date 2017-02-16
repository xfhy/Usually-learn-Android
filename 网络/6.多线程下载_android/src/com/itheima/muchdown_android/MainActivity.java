package com.itheima.muchdown_android;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.itheima.muchthredown_android.R;

/**
 * 2017年1月22日22:06:52
 * @author XFHY
 * 
 * 多线程下载服务器的资源
 * 使用ProgressBar显示进度      ProgressBar可以在子线程中更新UI
 * 
 * 还有很多细节没有考虑到    比如sdcard没有判断
 * 
 */
public class MainActivity extends Activity implements OnClickListener {

	private EditText et_thread_count;
	private Context mContext;
	private LinearLayout progress_layout;

	private int threadCount = 0;// 开启3个线程
	private int blockSize = 0;// 每个线程下载的大小
	private String path = "http://192.168.191.1:8080/itheima74/feiq.exe";

	private int runningThreadCount = 0;   //当前正在运行的线程的数目   当变成0时,表示文件全部下载完成
	
	private Map<Integer, ProgressBar> allProgressBar = new HashMap<>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;

		findViewById(R.id.bt_download).setOnClickListener(this);
		et_thread_count = (EditText) findViewById(R.id.et_thread_count);
		progress_layout = (LinearLayout) findViewById(R.id.progress_layout);
	}

	@Override
	public void onClick(View v) {
		// 获取用户输入的线程数
		String threadCountString = et_thread_count.getText().toString().trim();
		threadCount = Integer.parseInt(threadCountString);

		progress_layout.removeAllViews();   //清空所有的子控件
		
		for (int i = 0; i < threadCount; i++) {
			// 动态加载ProgressBar
			ProgressBar progressBar = (ProgressBar) View.inflate(mContext,
					R.layout.child_progress_layout, null);
			progress_layout.addView(progressBar);
			allProgressBar.put(i, progressBar);    //将添加的ProgressBar添加到Map中,待会儿方便使用
		}
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				download();  //下载
			}
		}).start();
	}

	/**
	 * 下载
	 */
	private void download(){
		try {
			URL url = new URL(path);

			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(10 * 1000);
			int code = connection.getResponseCode();
			if (code == 200) {
				System.out.println("成功访问服务器");

				// 1.要得到服务器资源的大小
				int fileLength = connection.getContentLength();

				// file : 文件； mode:文件的模式，rwd：直接写到底层设备，硬盘
				// 2.在本地创建一个与服务器资源同样大小的一个文件(占位)
				RandomAccessFile accessFile = new RandomAccessFile(new File(
						getFileName(path)), "rw");
				accessFile.setLength(fileLength); // 设置随机访问文件的大小
				accessFile.close();

				blockSize = fileLength / threadCount; // 每个线程下载的大小

				// 要分配每个线程下载文件的开始位置和结束位置
				for (int threadId = 0; threadId < threadCount; threadId++) {
					int startIndex = threadId * blockSize; // 计算每个线程的开始下载位置
					int endIndex = (threadId + 1) * blockSize - 1; // 计算每个线程的结束下载位置

					// 如果到了最后一个线程 则结束下载位置是fileLength-1 如果是最后一个线程，结束位置需要单独计算
					if (threadId == threadCount - 1) {
						endIndex = fileLength - 1;
					}

					// 4.开启线程去执行下载
					new DownloadThread(threadId, startIndex, endIndex).start();
				}

			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public class DownloadThread extends Thread {

		private int threadId;
		private int startIndex;
		private int endIndex;
		private int lastPosition; // 上一次下载到的那个位置
		private int currentThreadTotalProgress;   //当前进度条的最大值

		public DownloadThread(int threadId, int startIndex, int endIndex) {
			this.threadId = threadId;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
			currentThreadTotalProgress = endIndex-startIndex;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {

			ProgressBar progressBar = allProgressBar.get(threadId);
			synchronized (DownloadThread.class) {
				runningThreadCount++;     //当前正在下载的线程数目+1
			}
			
			// 分段请求网络连接，分段保存文件到本地
			try {
				URL url = new URL(path);
				HttpURLConnection connection = (HttpURLConnection) url
						.openConnection();
				connection.setRequestMethod("GET");
				connection.setConnectTimeout(10 * 1000);
				System.out.println("理论上下载：  线程：" + threadId + "，开始位置："
						+ startIndex + ";结束位置:" + endIndex);

				// 读取上次下载结束的位置,这次就从那里开始下载
//				File file2 = new File(getFilePath()+threadId + ".txt");
				;   
				if (SharedUtils.getLastPosition(mContext, threadId) != -1) { // 如果不是第一次下载
					
					/*BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(new FileInputStream(file2)));
					String lastPosition_str = bufferedReader.readLine();
					Log.i("xfhy", "lastPosition_str:"+lastPosition_str);
					lastPosition = Integer.parseInt(lastPosition_str); // 读取文件获取上次下载的位置
					
					bufferedReader.close();*/
					
					//从SharedPreferences文件中获取上一次下载到的那个位置
					lastPosition = SharedUtils.getLastPosition(mContext, threadId);  
					
					//说明该线程已经下载完成
					if(lastPosition == endIndex+1){
						progressBar.setProgress(currentThreadTotalProgress);   //设置进度为满的
						runningThreadCount--;
					}
					
				} else {  
					lastPosition = startIndex;
				}
				System.out.println("实际下载：  线程：" + threadId + "，开始位置："
						+ lastPosition + ";结束位置:" + endIndex);	
				// 设置分段下载的头信息。 Range:做分段数据请求用的。
				connection.setRequestProperty("Range", "bytes:"
						+ lastPosition + "-" + endIndex);// bytes:0-500:请求服务器资源中0-500之间的字节信息
															// 501-1000:
				
				// 200：请求全部资源成功， 206代表部分资源请求成功
				int code = connection.getResponseCode();
				if (code == 206) {
					InputStream inputStream = connection.getInputStream();

					// 请求成功将流写入本地文件中，已经创建的占位那个文件中
					RandomAccessFile randomAccessFile = new RandomAccessFile(
							new File(getFileName(path)), "rw");
					randomAccessFile.seek(lastPosition);   //指针移动到上次最后的那个位置上去

					// 将流中的数据写入文件
					byte[] buffer = new byte[1024*100];   //缓存区
					int length = -1;
					int total = 0; // 记录本次线程下载的总大小

					// 将从网络上获取的资源写入到本地文件中
					while ((length = inputStream.read(buffer)) != -1) {
						randomAccessFile.write(buffer, 0, length);

						total = total + length; // 每次保存了多少就将本次下载的总大小 加多少
						
						// 去保存当前线程下载的位置，保存到文件中
						int currentThreadPosition = lastPosition + total;   //从上一次最后下载到的那里开始
						
						/*
						// 创建随机文件保存当前线程下载的位置
						File file = new File(getFilePath()+threadId + ".txt"); // 将当前线程下载到哪个字节
																	// 位置保存到文件中
						RandomAccessFile randomAccessFile2 = new RandomAccessFile(
								file, "rwd");
						randomAccessFile2.write(String.valueOf(
								currentThreadPosition).getBytes());
						randomAccessFile2.close();*/
						
						//将当前下载到的那个位置保存到SharedPreferences文件中
						SharedUtils.setLastPosition(mContext, threadId, currentThreadPosition);
						
						//计算线程下载的进度并设置进度
						int currentProgress = currentThreadPosition-startIndex;   
						progressBar.setMax(currentThreadTotalProgress);
						progressBar.setProgress(currentProgress);
					}

					// 关闭相关的流信息
					inputStream.close();
					randomAccessFile.close();

					System.out.println("线程：" + threadId + "，下载完毕");
					
					//当所有线程下载结束，删除存放下载位置的文件。
					synchronized (DownloadThread.class) {
						runningThreadCount--; // 当前线程下载完毕后 需要把当前正在下载的线程数目-1     标志着一个线程下载结束。
						if(runningThreadCount == 0){
							
							//删除文件
							for (int i = 0; i < threadCount; i++) {
								File file = new File(getFilePath()+threadId+".txt");
								if (file.delete()) { // 删除该文件
									System.out.println("删除文件"+file.getName()+"成功");
								}
							} 
							
							runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									Toast.makeText(mContext, "下载完成", Toast.LENGTH_SHORT).show();
								}
							});
							
							
						}
					}
					
				}

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			super.run();
		}
	}

	private String getFilePath(){
		return Environment.getExternalStorageDirectory()+"/";
	}
	
	//获取需要保存的文件名
	private String getFileName(String path){
		//保存到sd卡中
		return Environment.getExternalStorageDirectory()+"/"+path.substring(path.lastIndexOf("/"));    //截取最后一个 / 后面的字符串
	}
	
}
