import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class HdfsWriter extends Configured implements Tool {
	public static final String FS_PARAM_NAME = "fs.defaultFS";

	@Override
	public int run(String[] arg0) throws Exception {
		// TODO Auto-generated method stub
		if(arg0.length<2) {
			System.err.println("error!!! give exactly 2 arguments");
			return 1;
		}
		
		String LocalPath = arg0[0].toString();
		Path OutputPath = new Path(arg0[1]);
		Configuration conf = getConf();
		FileSystem fs = FileSystem.get(conf);
		OutputStream os = fs.create(OutputPath);
		InputStream is = new BufferedInputStream(new FileInputStream(LocalPath));
		IOUtils.copyBytes(is,os,conf);
		return 0;
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int returncode=ToolRunner.run(new HdfsWriter(), args);
		System.exit(returncode);
	}

}
