import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;


public class Utilitaires {

	public Utilitaires() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		 System.out.println(readLine2(System.in));
		
		
		//System.out.println("helo");
	}
	public static byte[] readFull(InputStream in) throws IOException{
		
		int returnvalue;
		String rs = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
		byte[] buffer =new byte[1024];
		byte[] result =new byte[in.available()];
		byte[] bytes = null;
		while((returnvalue = in.read(buffer,0,1024 ))!=-1 ){
			baos.write(buffer, 0, returnvalue);
		
		}
		baos.flush();
		baos.close();
		in.close();
		 
		bytes = baos.toByteArray();
		return bytes;
		
	}
	public static String readLine2(InputStream in) throws IOException{
		StringBuilder build = new StringBuilder();
		byte[] buf = new byte[1024];
		int length;
		try (InputStream is = in) {
		  while ((length = is.read(buf)) != -1) {
			  build.append(new String(buf, 0, length));
			  //System.out.println(build.toString());
			  
			  if(new String(buf, 0, length).toString().trim().equals("/n")){
				  break;
				  
			  }
		  }
		}
		return build.toString();
	}
	 

}
