package Day16;
import com.github.yunusmete.stf.api.STFService;
import com.github.yunusmete.stf.api.ServiceGenerator;
import com.github.yunusmete.stf.model.Device;
import com.github.yunusmete.stf.rest.DeviceResponse;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.testng.TestNG;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Runner {


	public static String ACCESS_TOKEN = "36576328289c4d06a0fff3ea037e7158a012da363139401aa987ba92d409a162";



	public static void createTestNgXml(List<String> devices, String className){
		//创建文档
		Document document = DocumentHelper.createDocument();
		//创建节点
		Element root = DocumentHelper.createElement("suite");
		//设置根节点
		document.setRootElement(root);

		int deviceCount = devices.size();


		root.addAttribute("name", "concurrentTest");
		root.addAttribute("parallel", "tests");
		root.addAttribute("thread-count", String.valueOf(deviceCount));
		for(int i = 0; i < deviceCount; i++){
			Element test = root.addElement("test");
			test.addAttribute("name", devices.get(i));

			Element parameter = test.addElement("parameter");
			parameter.addAttribute("name","udid");
			parameter.addAttribute("value",devices.get(i));

			Element classes = test.addElement("classes");
			Element classElement = classes.addElement("class");
			classElement.addAttribute("name", className);
		}
		String xmlPath = System.getProperty("user.dir") + "\\testng.xml";
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter writer;
		try {
			writer = new XMLWriter(new FileWriter(xmlPath), format);
			writer.write(document);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	public static List<String> getDevice ()  {

		List<String> devices = new ArrayList<>();
		String line;
		StringBuilder log = new StringBuilder();
		Process process;
		Runtime rt = Runtime.getRuntime();
		try {
			process = rt.exec(new String[]
					{"adb", "devices", "-l"});
			BufferedReader stdInput = new BufferedReader(new
					InputStreamReader(
					process.getInputStream()));
			BufferedReader stdError = new BufferedReader(new
					InputStreamReader(
					process.getErrorStream()));


			while ((line = stdInput.readLine()) != null) {
				log.append(line);
				log.append(System.getProperty
						("line.separator"));
			}
			while ((line = stdError.readLine()) != null) {
				log.append(line);
				log.append(System.getProperty
						("line.separator"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Scanner scan = new Scanner(String.valueOf(log));
		while (scan.hasNextLine()) {
			String oneLine = scan.nextLine();
			if (oneLine.contains("model")) {
				devices.add(oneLine.split("device")[0].trim());
			}
		}
		return devices;
	}


	//   连接STF平台可以进行远程调试的设备。
	public static void adbConn () throws  IOException {
		List<String> list = new ArrayList<String>();
		STFService service = ServiceGenerator.createService(STFService.class, "http://192.168.1.111:7100/api/v1", ACCESS_TOKEN);
		DeviceResponse devices = service.getDevices();
		List<Device> deviceList = devices.getDevices();
		for (Device device : deviceList) {
			if (device.isUsing()) {
				list.add((String) device.getRemoteConnectUrl());
			}
		}
		for (String str : list) {
			String s = "adb connect" + " " + str;
			Runtime.getRuntime().exec(s);
		}
	}


	public static void main(String[] args) throws IOException {
			adbConn();
		System.out.println("链接STF平台成功");
		List<String> devices  =	getDevice();
		System.out.println("获取本地连接到stf平台的正在运行并且可以远程调试设备id成功");
		createTestNgXml(devices,"Day16.TestGird");
		System.out.println("创建testng 运行文件成功！");
		TestNG testNG = new TestNG();
		List<String> suites = new ArrayList<String>();
		String  xml  =	System.getProperty("user.dir") + "\\testng.xml";
		suites.add(xml);
		testNG.setTestSuites(suites);
		//运行并发
		testNG.run();


	}


}
