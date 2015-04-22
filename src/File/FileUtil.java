package File;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.util.Date;

import com.mysql.jdbc.log.LogUtils;

/**
 * 
 * 功能描述：
 * 
 * @author Administrator
 * @Date Jul 19, 2008
 * @Time 9:46:11 AM
 * @version 1.0
 */
public class FileUtil {

	/**
	 * 功能描述：列出某文件夹及其子文件夹下面的文件，并可根据扩展名过滤
	 * 
	 * @param path
	 *            文件夹
	 */
	public static void list(File path) {
		if (!path.exists()) {
			System.out.println("文件名称不存在!");
		} else {
			if (path.isFile()) {
				if (path.getName().toLowerCase().endsWith(".pdf")
						|| path.getName().toLowerCase().endsWith(".doc")
						|| path.getName().toLowerCase().endsWith(".chm")
						|| path.getName().toLowerCase().endsWith(".html")
						|| path.getName().toLowerCase().endsWith(".htm")) {// 文件格式
					System.out.println(path);
					System.out.println(path.getName());
				}
			} else {
				File[] files = path.listFiles();
				for (int i = 0; i < files.length; i++) {
					list(files[i]);
				}
			}
		}
	}

	/**
	 * 功能描述：拷贝一个目录或者文件到指定路径下，即把源文件拷贝到目标文件路径下
	 * 
	 * @param source
	 *            源文件
	 * @param target
	 *            目标文件路径
	 * @return void
	 */
	public static void copy(File source, File target) {
		File tarpath = new File(target, source.getName());
		if (source.isDirectory()) {
			tarpath.mkdir();
			File[] dir = source.listFiles();
			for (int i = 0; i < dir.length; i++) {
				copy(dir[i], tarpath);
			}
		} else {
			try {
				InputStream is = new FileInputStream(source); // 用于读取文件的原始字节流
				OutputStream os = new FileOutputStream(tarpath); // 用于写入文件的原始字节的流
				byte[] buf = new byte[1024];// 存储读取数据的缓冲区大小
				int len = 0;
				while ((len = is.read(buf)) != -1) {
					os.write(buf, 0, len);
				}
				is.close();
				os.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	 /**
     * 复制文件夹的文件到另一个文件夹
     * @param originDirectory
     * @param targetDirectory
     */
    public static void copyFolder(String originDirectory,String targetDirectory){
        File origindirectory = new File(originDirectory);   //源路径File实例
        File targetdirectory = new File(targetDirectory);  //目标路径File实例
        if(!origindirectory.isDirectory() || !targetdirectory.isDirectory()){    //判断是不是正确的路径
                    System.out.println("不是正确的目录！");
//                    LogUtils.e("不是正确的目录！");
                    return;
        }
        File[] fileList = origindirectory.listFiles();  //目录中的所有文件
        for(File file : fileList){
                  if(!file.isFile())   //判断是不是文件
                  continue;
                  System.out.println(file.getName());
//                  LogUtils.e("fileName "+file.getName());
                  try{
                           FileInputStream fin = new FileInputStream(file);
                           BufferedInputStream bin = new BufferedInputStream(fin);
                           PrintStream pout = new PrintStream(targetdirectory.getAbsolutePath()+"/"+file.getName());
                           BufferedOutputStream bout = new BufferedOutputStream(pout);
                           int total =bin.available();  //文件的总大小
                           int percent = total/100;    //文件总量的百分之一
                           int count;
                           while((count = bin.available())!= 0){
                                      int c = bin.read();  //从输入流中读一个字节
                                      bout.write((char)c);  //将字节（字符）写到输出流中     

                                      if(((total-count) % percent) == 0){
                                               double d = (double)(total-count) / total; //必须强制转换成double
//                                               System.out.println(Math.round(d*100)+"%"); //输出百分比进度
//                                               LogUtils.e(Math.round(d*100)+"%");
                                       }
                           }
                           bout.close();
                           pout.close();
                           bin.close();
                           fin.close();
                  }catch(IOException e){
                           e.printStackTrace();
                  }
       }
      System.out.println("End");
//      LogUtils.e("end---------------------");
      
}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		File file = new File("D:\\个人资料\\MySQL 5");
//		list(file);
//		Date myDate = new Date(); 
//		DateFormat df = DateFormat.getDateInstance();
//		System.out.println(df.format(myDate)); 
		
		copyFolder("F:/testCopy","F:/TestCopyTag");
		
	}

}
