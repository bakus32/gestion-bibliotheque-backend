///*
// * Created on 2022-10-21 ( Time 23:35:15 )
// * Generator tool : Telosys Tools Generator ( version 3.3.0 )
// * Copyright 2019 Smile Backend generator. All Rights Reserved.
// */
//
//package ci.gs2e.biblio.helper;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.Locale;
//import java.util.Vector;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import com.jcraft.jsch.Channel;
//import com.jcraft.jsch.ChannelExec;
//import com.jcraft.jsch.ChannelSftp;
//import com.jcraft.jsch.JSch;
//import com.jcraft.jsch.JSchException;
//import com.jcraft.jsch.Session;
//import com.jcraft.jsch.SftpException;
//
//import ci.gs2e.biblio.helper.contrat.Response;
//
///**
// * Remote files managment utilities
// * 
// * @author Lazare Yao
// *
// */
//@Component
//@Service
//public class RemoteFileManager {
//
//	private static Logger           slf4jLogger          = LoggerFactory.getLogger(Utilities.class);
//
//
//
//
//	public static <T> Response<T> sendFileToSftpServer(String host, Integer port, String username, String password, String sftpWorkDir, String filePath, boolean renameFileIfExists, String newExistingFileName, TechnicalError technicalError, Locale locale) {
//		Response<T> response = new Response<T>();
//
//		Session session = null;
//		Channel channel = null;
//		ChannelSftp channelSftp = null;
//		//SftpATTRS attrs =null;
//
//		slf4jLogger.info("preparing the host information for sftp.");
//
//		try {
//			JSch jsch = new JSch();
//
//			java.util.Properties config = new java.util.Properties();
//			config.put("StrictHostKeyChecking", "no");
//
//			session = jsch.getSession(username, host, port);
//			session.setPassword(password);
//			session.setConfig(config);
//			session.connect();
//
//			channel = session.openChannel("sftp");
//			channel.connect();
//			channelSftp = (ChannelSftp) channel;
//
//			String [] directories = sftpWorkDir.split("/");
//			String finalDirectory = "";
//			for (String directory : directories) 
//			{
//				if(Utilities.isNotBlank(directory))
//				{
//					finalDirectory += "/"+directory; 
//					try {
//						channelSftp.mkdir(finalDirectory);
//						channelSftp.cd(finalDirectory);
//					} catch (Exception e) {
//						channelSftp.cd(finalDirectory);
//					}
//				}
//			}			
//			channelSftp.cd(finalDirectory);
//
//			sftpWorkDir = Utilities.addSlash(sftpWorkDir);
//			File file = new File(filePath);
//			if(renameFileIfExists && Utilities.isNotBlank(newExistingFileName)) {
//				String remoteFilePath = sftpWorkDir+file.getName();
//				if(exists(channelSftp, remoteFilePath)) {
//					channelSftp.rename(remoteFilePath, sftpWorkDir+newExistingFileName);
//				}
//			}
//
//			channelSftp.put(new FileInputStream(file), file.getName());
//
//			response.setHasError(false);
//
//			slf4jLogger.info("File '" + filePath + "' transfered successfully to host.");
//		} catch (Exception e) {
//			e.printStackTrace();
//			response.setHasError(true);
//			response.setStatus(technicalError.CONNEXION_TO_SFTP_ERROR( "serveur SFTP -> "+host+", port -> "+port+",  message -> "+e.getMessage(), locale));
//		} finally {
//			if(channelSftp != null) {
//				channelSftp.exit();
//				slf4jLogger.info("sftp Channel exited.");
//			}
//			if(channel != null) {
//				channel.disconnect();
//				slf4jLogger.info("Channel disconnected.");
//			}
//			if(session != null) {
//				session.disconnect();
//				slf4jLogger.info("Host Session disconnected.");
//			}
//		}
//
//		return response;
//	}
//
//	public static void downloadFileBySFtp(String userName, String password, String host, int port, String remoteFilePath, String localDirectory) {
//		Session session = null;
//		Channel channel = null;
//		ChannelSftp channelSftp = null;
//		try {
//			JSch ssh = new JSch();
//			JSch.setConfig("StrictHostKeyChecking", "no");
//			session = ssh.getSession(userName, host, port);
//			session.setPassword(password);
//			session.connect();
//			channel = session.openChannel("sftp");
//			channel.connect();
//			channelSftp = (ChannelSftp) channel;
//			channelSftp.get(remoteFilePath, localDirectory);
//		} catch (JSchException e) {
//			System.out.println(userName);
//			e.printStackTrace();
//		} catch (SftpException e) {
//			System.out.println(userName);
//			e.printStackTrace();
//		} 
//		finally {
//			if(channelSftp != null) {
//				channelSftp.exit();
//				slf4jLogger.info("sftp Channel exited.");
//			}
//			if(channel != null) {
//				channel.disconnect();
//				slf4jLogger.info("Channel disconnected.");
//			}
//			if(session != null) {
//				session.disconnect();
//				slf4jLogger.info("Host Session disconnected.");
//			}
//		}
//
//	}
//
//	public static boolean exists(ChannelSftp channelSftp, String path) {
//		Vector res = null;
//		try {
//			res = channelSftp.ls(path);
//		} catch (SftpException e) {
//			if (e.id == ChannelSftp.SSH_FX_NO_SUCH_FILE) {
//				return false;
//			}
//			throw new UnknownError("Unexpected exception during ls files on sftp: ["+e.id+":"+e.getMessage()+"]");
//		}
//		return res != null && !res.isEmpty();
//	}
//
//	/**
//	 * Create a session by providing the private key for the authentication
//	 * 
//	 * @param privateKeyFilePath filename of the private key.
//	 * @param passphrase passphrase for <code>privateKeyFilePath</code>.
//	 * @author lazareyao
//	 * */
//	public static Session createSSHsession(String user, String host, int port, String privateKeyFilePath, String passphrase) {
//		try {
//			JSch jsch = new JSch();
//
//			if (privateKeyFilePath != null) {
//				if (passphrase != null) {
//					jsch.addIdentity(privateKeyFilePath, passphrase);
//				} else {
//					jsch.addIdentity(privateKeyFilePath);
//				}
//			}
//
//			java.util.Properties config = new java.util.Properties();
//			config.put("StrictHostKeyChecking", "no");
//
//			Session session = jsch.getSession(user, host, port);
//			session.setConfig(config);
//			session.connect();
//
//			return session;
//		} catch (JSchException e) {
//			System.out.println(e);
//			return null;
//		}
//	}
//
//	/**
//	 * SCP files from the remote host to localhost.
//	 * 
//	 * @param session 		The session he has created using {@link #createSSHsession}
//	 * @param from 			The remonte file directory
//	 * @param to 			The local directory to copy the remote file in
//	 * @param fileName		The remote file name
//	 * @author 				lazareyao
//	 * 
//	 * @see #createSSHsession(String user, String host, int port, String privateKeyFilePath, String passphrase)
//	 * */
//	public static void copyRemoteToLocalBySCP(Session session, String from, String to, String fileName) throws JSchException, IOException {
//		from = from + File.separator + fileName;
//		String prefix = null;
//
//		if (new File(to).isDirectory()) {
//			prefix = to + File.separator;
//		}
//
//		// exec 'scp -f rfile' remotely
//		String command = "scp -f " + from;
//		Channel channel = session.openChannel("exec");
//		((ChannelExec) channel).setCommand(command);
//
//		// get I/O streams for remote scp
//		OutputStream out = channel.getOutputStream();
//		InputStream in = channel.getInputStream();
//
//		channel.connect();
//
//		byte[] buf = new byte[1024];
//
//		// send '\0'
//		buf[0] = 0;
//		out.write(buf, 0, 1);
//		out.flush();
//
//		while (true) {
//			int c = checkAck(in);
//			if (c != 'C') {
//				break;
//			}
//
//			// read '0644 '
//			in.read(buf, 0, 5);
//
//			long filesize = 0L;
//			while (true) {
//				if (in.read(buf, 0, 1) < 0) {
//					// error
//					break;
//				}
//				if (buf[0] == ' ') break;
//				filesize = filesize * 10L + (long) (buf[0] - '0');
//			}
//
//			String file = null;
//			for (int i = 0; ; i++) {
//				in.read(buf, i, 1);
//				if (buf[i] == (byte) 0x0a) {
//					file = new String(buf, 0, i);
//					break;
//				}
//			}
//
//			System.out.println("file-size=" + filesize + ", file=" + file);
//
//			// send '\0'
//			buf[0] = 0;
//			out.write(buf, 0, 1);
//			out.flush();
//
//			// read a content of lfile
//			FileOutputStream fos = new FileOutputStream(prefix == null ? to : prefix + file);
//			int foo;
//			while (true) {
//				if (buf.length < filesize) foo = buf.length;
//				else foo = (int) filesize;
//				foo = in.read(buf, 0, foo);
//				if (foo < 0) {
//					// error
//					break;
//				}
//				fos.write(buf, 0, foo);
//				filesize -= foo;
//				if (filesize == 0L) break;
//			}
//
//			if (checkAck(in) != 0) {
//				System.exit(0);
//			}
//
//			// send '\0'
//			buf[0] = 0;
//			out.write(buf, 0, 1);
//			out.flush();
//
//			try {
//				if (fos != null) fos.close();
//			} catch (Exception ex) {
//				System.out.println(ex);
//			}
//		}
//
//		channel.disconnect();
//		session.disconnect();
//	}
//
//	/**
//	 * SCP files from localhost to a remote host.
//	 * 
//	 * @param session 		The session he has created using {@link #createSSHsession}
//	 * @param from 			The local file directory
//	 * @param to 			The remote directory to copy the local file in
//	 * @param fileName		The local file name
//	 * @author 				lazareyao
//	 * 
//	 * @see #createSSHsession(String user, String host, int port, String privateKeyFilePath, String passphrase)
//	 * */
//	public static void copyLocalToRemoteBySCP(Session session, String from, String to, String fileName) throws JSchException, IOException {
//		boolean ptimestamp = true;
//		from = from + File.separator + fileName;
//
//		// exec 'scp -t rfile' remotely
//		String command = "scp " + (ptimestamp ? "-p" : "") + " -t " + to;
//		Channel channel = session.openChannel("exec");
//		((ChannelExec) channel).setCommand(command);
//
//		// get I/O streams for remote scp
//		OutputStream out = channel.getOutputStream();
//		InputStream in = channel.getInputStream();
//
//		channel.connect();
//
//		if (checkAck(in) != 0) {
//			System.exit(0);
//		}
//
//		File _lfile = new File(from);
//
//		if (ptimestamp) {
//			command = "T" + (_lfile.lastModified() / 1000) + " 0";
//			// The access time should be sent here,
//			// but it is not accessible with JavaAPI ;-<
//			command += (" " + (_lfile.lastModified() / 1000) + " 0\n");
//			out.write(command.getBytes());
//			out.flush();
//			if (checkAck(in) != 0) {
//				System.exit(0);
//			}
//		}
//
//		// send "C0644 filesize filename", where filename should not include '/'
//		long filesize = _lfile.length();
//		command = "C0644 " + filesize + " ";
//		if (from.lastIndexOf('/') > 0) {
//			command += from.substring(from.lastIndexOf('/') + 1);
//		} else {
//			command += from;
//		}
//
//		command += "\n";
//		out.write(command.getBytes());
//		out.flush();
//
//		if (checkAck(in) != 0) {
//			System.exit(0);
//		}
//
//		// send a content of lfile
//		FileInputStream fis = new FileInputStream(from);
//		byte[] buf = new byte[1024];
//		while (true) {
//			int len = fis.read(buf, 0, buf.length);
//			if (len <= 0) break;
//			out.write(buf, 0, len); //out.flush();
//		}
//
//		// send '\0'
//		buf[0] = 0;
//		out.write(buf, 0, 1);
//		out.flush();
//
//		if (checkAck(in) != 0) {
//			System.exit(0);
//		}
//		out.close();
//
//		try {
//			if (fis != null) fis.close();
//		} catch (Exception ex) {
//			System.out.println(ex);
//		}
//
//		channel.disconnect();
//		session.disconnect();
//	}
//
//
//	public static int checkAck(InputStream in) throws IOException {
//		int b = in.read();
//		// b may be 0 for success,
//		//          1 for error,
//		//          2 for fatal error,
//		//         -1
//		if (b == 0) return b;
//		if (b == -1) return b;
//
//		if (b == 1 || b == 2) {
//			StringBuffer sb = new StringBuffer();
//			int c;
//			do {
//				c = in.read();
//				sb.append((char) c);
//			}
//			while (c != '\n');
//			if (b == 1) { // error
//				System.out.print(sb.toString());
//			}
//			if (b == 2) { // fatal error
//				System.out.print(sb.toString());
//			}
//		}
//		return b;
//	}
//
//
//
//
//
//
//
//
//
//
//
//
//
//}
//
