package Day14;

import com.github.yunusmete.stf.api.STFService;
import com.github.yunusmete.stf.api.ServiceGenerator;
import com.github.yunusmete.stf.model.Device;
import com.github.yunusmete.stf.model.DeviceBody;
import com.github.yunusmete.stf.rest.DeviceResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunusm on 07.11.2016.
 */
public final class STF {

    private static final String ACCESS_TOKEN = "36576328289c4d06a0fff3ea037e7158a012da363139401aa987ba92d409a162";




//   正在使用中的设备，才能接受远程本地调试，
    public static List<String> getUseDevice() {


        List<String>  list  = new ArrayList<String>();


        STFService service = ServiceGenerator.createService(STFService.class, "http://192.168.1.111:7100/api/v1", ACCESS_TOKEN);
        DeviceResponse devices = service.getDevices();
        List<Device> deviceList = devices.getDevices();
        for (Device device : deviceList) {
//                    System.out.println(device.getSerial()); //获取存在过所有device id

//                      System.out.println(  device.getModel()); //获得模型的名称




//            if (device.isUsing()) {
//
//                device.getStatusChangedAt();
//
//            }



//                      service.addDeviceToUser(new DeviceBody(device.getSerial(), 900000));



            //是否存活，没在使用的设备，进行使用
              if (device.isPresent()) //是否存活
                  if (device.isUsing() ==false)
                      service.addDeviceToUser(new DeviceBody(device.getSerial(), 900000));









//                  System.out.println(device.getSerial());

//            System.out.println(  device.getPlatform()); //获取系统版本
//            System.out.println( device.getStatus()); //状态这个有bug
//            System.out.println(  device.getVersion());//获得安卓系统版本
//            System.out.println(  device.getAbi()); //获得处理器名称
//            System.out.println(  device.getPhone()); //获得IMEI



            //获取正在使用的device的id

//            if (device.isUsing())
//                System.out.println(device.getSerial());


//            System.out.println(device.getOwner());//获取这个登陆者的名称和邮箱。

//            System.out.println(device.getBrowser()); //或者系统浏览器的信息


//              System.out.println(device.getChannel()); //获得通道序列


//                System.out.println(device.getBattery());  //获得电池信息
//            System.out.println(   device.getCreatedAt()); //获得创建时间


//            System.out.println(  device.getDisplay()); //获得设备显示属性信息

//            System.out.println( device.getManufacturer());//获得手机品牌


//            System.out.println( device.getNetwork()); //获取网络的具体信息


//            System.out.println(   device.getOperator());//获取手机卡的移动网络名称


//            System.out.println(device.getProvider()); //获取提供商，stf服务器电脑的用户名

//            System.out.println(      device.getRemoteConnectUrl()); //获得可以远程调试的链接。用于adb connect


//            System.out.println( device.getSdk()); //获得系统SDK版本


//            System.out.println( device.isAirplaneMode()); //是否是飞行模式


//            System.out.println( device.isReady()); //是否可读的



            //判断是否可以应用远程连接的代码

//            if (device.isRemoteConnect())
//                System.out.println(device.getSerial());






                list.add( (String) device.getRemoteConnectUrl());







        }
        return list;
    }



    public static void adbcon() throws IOException {

        List<String>  list  = new ArrayList<String>();

        list= getUseDevice();


        for(String str : list) {

            String s ="adb connect"+" "+str ;
            Runtime.getRuntime().exec(s);




        }






    }


    public static void main(String[] args) throws IOException {

        List<String>  list  = new ArrayList<String>();

        list= getUseDevice();

//
//        for(String str : list) {
//
//            System.out.println(str);
//
//
//
//
//        }


    }






}
