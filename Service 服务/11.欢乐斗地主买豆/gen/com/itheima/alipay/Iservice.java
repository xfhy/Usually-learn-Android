/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: F:\\eclipse集成开发环境\\WorkSpace\\服务\\11.欢乐斗地主买豆\\src\\com\\itheima\\alipay\\Iservice.aidl
 */
package com.itheima.alipay;
/**
 * @author  XFHY
 * @date  2017骞�2鏈�1鏃� 涓嬪崍1:33:22
 * @package com.itheima.alipay
 * @function 
 */
public interface Iservice extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.itheima.alipay.Iservice
{
private static final java.lang.String DESCRIPTOR = "com.itheima.alipay.Iservice";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.itheima.alipay.Iservice interface,
 * generating a proxy if needed.
 */
public static com.itheima.alipay.Iservice asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.itheima.alipay.Iservice))) {
return ((com.itheima.alipay.Iservice)iin);
}
return new com.itheima.alipay.Iservice.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_callPay:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
int _arg2;
_arg2 = data.readInt();
boolean _result = this.callPay(_arg0, _arg1, _arg2);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.itheima.alipay.Iservice
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public boolean callPay(java.lang.String name, java.lang.String pwd, int money) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(name);
_data.writeString(pwd);
_data.writeInt(money);
mRemote.transact(Stub.TRANSACTION_callPay, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_callPay = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public boolean callPay(java.lang.String name, java.lang.String pwd, int money) throws android.os.RemoteException;
}
