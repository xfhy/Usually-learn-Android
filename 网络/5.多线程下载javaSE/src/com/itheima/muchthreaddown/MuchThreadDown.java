package com.itheima.muchthreaddown;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 2017年1月20日
 * 
 * XFHY
 * 
 * 用javaSE去下载服务器上的资源
 * 多线程下载
 * 并实现断点续传
 */
public class MuchThreadDown {

	private static int threadCount = 3;// 开启3个线程
	private static int blockSize = 0;// 每个线程下载的大小
	private static String path = "http://192.168.191.1:8080/itheima74/360.exe";

	private static int runningThreadCount = 0;   //当前正在运行的线程的数目   当变成0时,表示文件全部下载完成
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// http://192.168.191.1:8080/itheima74/feiq.exe
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

	public static class DownloadThread extends Thread {

		private int threadId;
		private int startIndex;
		private int endIndex;
		private int lastPosition; // 上一次下载到的那个位置

		public DownloadThread(int threadId, int startIndex, int endIndex) {
			this.threadId = threadId;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {

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
				File file2 = new File(threadId + ".txt");
				if (file2.exists()) { // 如果文件存在        则是第(>=)二次进行下载
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(new FileInputStream(file2)));
					String lastPosition_str = bufferedReader.readLine();
					lastPosition = Integer.parseInt(lastPosition_str); // 读取文件获取上次下载的位置
					
					bufferedReader.close();
				} else {  //如果是第一次下载
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
					byte[] buffer = new byte[1024*10];   //缓存区
					int length = -1;
					int total = 0; // 记录本次线程下载的总大小

					// 将从网络上获取的资源写入到本地文件中
					while ((length = inputStream.read(buffer)) != -1) {
						randomAccessFile.write(buffer, 0, length);

						total = total + length; // 每次保存了多少就将本次下载的总大小 加多少
						// 去保存当前线程下载的位置，保存到文件中
						int currentThreadPosition = lastPosition + total;   //从上一次最后下载到的那里开始
						// 创建随机文件保存当前线程下载的位置
						File file = new File(threadId + ".txt"); // 将当前线程下载到哪个字节
																	// 位置保存到文件中
						RandomAccessFile randomAccessFile2 = new RandomAccessFile(
								file, "rwd");
						randomAccessFile2.write(String.valueOf(
								currentThreadPosition).getBytes());
						randomAccessFile2.close();
					}

					// 关闭相关的流信息
					inputStream.close();
					randomAccessFile.close();

					System.out.println("线程：" + threadId + "，下载完毕");
					
					//当所有线程下载结束，删除存放下载位置的文件。
					synchronized (DownloadThread.class) {
						runningThreadCount--; // 当前线程下载完毕后 需要把当前正在下载的线程数目-1     标志着一个线程下载结束。
						for (int i = 0; i < threadCount; i++) {
							File file = new File(threadId+".txt");
							file.delete();    //删除该文件
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

	//获取需要保存的文件名
	private static String getFileName(String path){
		return path.substring(path.lastIndexOf("/"));    //截取最后一个 / 后面的字符串
	}
	
}
