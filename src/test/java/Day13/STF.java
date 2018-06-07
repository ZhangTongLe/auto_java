package Day13;

import com.github.yunusmete.stf.api.STFService;
import com.github.yunusmete.stf.api.ServiceGenerator;
import com.github.yunusmete.stf.model.Device;
import com.github.yunusmete.stf.model.DeviceBody;
import com.github.yunusmete.stf.rest.DeviceResponse;

import java.util.List;

/**
 * Created by yunusm on 07.11.2016.
 */
public class STF {

    private static final String ACCESS_TOKEN = "36576328289c4d06a0fff3ea037e7158a012da363139401aa987ba92d409a162";

    public static void main(String[] args) {
        STFService service = ServiceGenerator.createService(STFService.class, "http://192.168.1.111:7100/api/v1", ACCESS_TOKEN);
        DeviceResponse devices = service.getDevices();
        List<Device> deviceList = devices.getDevices();
        for (Device device : deviceList) {
            if (device.isPresent()) {
                if (device.getStatus()== 0)


                    service.addDeviceToUser(new DeviceBody(device.getSerial(), 900000));
                    System.out.println(device.getSerial());
                    AppiumTest test = new AppiumTest();
                    test.startTest(device.getSerial());
                    service.deleteDeviceBySerial(device.getSerial());
            }
        }
    }
}