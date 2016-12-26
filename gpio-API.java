
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GPIOControl {

	public static String gpiopath = "/sys/class/gpio";

	public static void export(int gpio) {
		String gptxt = String.valueOf(gpio);
		File f = new File(gpiopath + "/export");
		PrintWriter w;

		try {
			checkFile(f);
			w = new PrintWriter(f);
			w.write(gptxt);
			w.flush();
			w.close();

		} catch (FileNotFoundException e) {

		}

	}

	public static void unexport(int gpio) {
		String gptxt = String.valueOf(gpio);
		File f = new File(gpiopath + "/unexport");
		PrintWriter w;
		try {
			w = new PrintWriter(f);
			w.write(gptxt);
			w.flush();
			w.close();

		} catch (FileNotFoundException e) {
			checkFile(f);
		}

	}

	public static void direction(int gpio, String direction) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {

			e1.printStackTrace();
		}
		String gptxt = String.valueOf(gpio);
		File f = new File(gpiopath + "/gpio" + gptxt + "/direction");

		try {
			PrintWriter w = new PrintWriter(f);
			w.write(direction);
			w.flush();
			w.close();

		} catch (FileNotFoundException e) {
			checkFile(f);
		}

	}

	public static void wride(int gpio, int value) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {

			e1.printStackTrace();
		}
		String gptxt = String.valueOf(gpio);
		File f = new File(gpiopath + "/gpio" + gptxt + "/value");

		try {
			PrintWriter w = new PrintWriter(f);
			w.write(value);
			w.flush();
			w.close();
		} catch (FileNotFoundException e) {
			checkFile(f);
		}

	}

	public static int read(int gpio) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {

			e1.printStackTrace();
		}
		String gptxt = String.valueOf(gpio);
		File f = new File(gpiopath + "/gpio" + gptxt + "/value");

		try {
			Scanner src = new Scanner(f);
			if (src.hasNextInt()) {
				return src.nextInt();
			}
		} catch (FileNotFoundException e) {
			checkFile(f);

		}

		return 0000;

	}

	public static void checkFile(File file) {
		if (!file.exists()) {
			throw new GPIOExeption("File not exist!");
		}
		if (!file.isFile()) {
			throw new GPIOExeption("It is not a File!");
		}
		if (!file.canRead()) {
			throw new GPIOExeption("Can not read the File!");
		}
		if (!file.canWrite()) {
			throw new GPIOExeption("Can not write the File");
		}
	}

}

class GPIOExeption extends RuntimeException {
	public GPIOExeption(String error) {
		super(error);
	}
}
