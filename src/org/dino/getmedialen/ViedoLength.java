package org.dino.getmedialen;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import java.io.File;
//import java.io.PrintStream;
import java.util.ArrayList;

public class ViedoLength
{
  private static ArrayList filelist = new ArrayList();
  long ls = 0L;
  static long all = 0L;

  public static void main(String[] args)
  {
    long a = System.currentTimeMillis();
    //refreshFileList("E:\\XXXX\\XXXX");
    refreshFileList("E:\\videos\\films\\matrix");
    System.out.println("总时间:"+(System.currentTimeMillis()-a));
  }

  public static void refreshFileList(String strPath)
  {
    File dir = new File(strPath);
    File[] files = dir.listFiles();
    long ls = 0L;
    if (files == null)
      return;
    for (int i = 0; i < files.length; ++i) {
      if (files[i].isDirectory()) {
        refreshFileList(files[i].getAbsolutePath());
      } else {
        String strFileName = files[i].getAbsolutePath().toLowerCase();
        System.out.println("---" + strFileName);
        filelist.add(files[i].getAbsolutePath());
        File source = new File(strFileName);
        Encoder encoder = new Encoder();
        try {
          MultimediaInfo m = encoder.getInfo(source);
          ls = m.getDuration();
          all += ls;
          System.out.println(ls);
          System.out.println("此视频时长为:" + ls / 60000L + "分" + 
            (ls - ls / 60000L * 60000L) / 1000L + "秒！");
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    System.out.println(all);
    long hour = all / 3600000L;
    long minute = (all - all / 3600000L * 3600000L) / 60000L;
    long second = (all - hour * 3600000L - minute * 60000L) / 1000L;

    System.out.println("总视频时长为:" + hour + "小时" + minute + "分" + second + 
      "秒！");
  }
}