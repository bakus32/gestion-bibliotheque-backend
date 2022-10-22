/*
 * Created on 2022-10-21 ( Time 23:35:15 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Smile Backend generator. All Rights Reserved.
 */

package ci.gs2e.biblio.helper;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.LocaleUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.joda.time.format.DateTimeFormat;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;

import ci.gs2e.biblio.helper.contrat.SearchParam;
import ci.gs2e.biblio.helper.dto.ParameterDto;
import ci.gs2e.biblio.helper.dto.customize._FileDto;
import ci.gs2e.biblio.helper.enums.ExtensionEnum;
import ci.gs2e.biblio.helper.enums.OperatorEnum;

/**
 * Utilities
 * 
 * @author Smile Backend generator
 *
 */
public class Utilities {

	static final String   alphaNum      	= "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static final String   hexaAlphabet      = "0123456789abcdef";
	static SecureRandom   rnd      			= new SecureRandom();

	private static SimpleDateFormat _dateFormat          = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat _dateFormatMois      = new SimpleDateFormat("MM/yyyy");
	private static SimpleDateFormat _dateFormat_yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	private static Logger           slf4jLogger          = LoggerFactory.getLogger(Utilities.class);
	
	private static List<String> listeBase = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
	private static final String[] IP_HEADER_CANDIDATES = {"X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP", "HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR"};
	
	public static List<String>	URI_AS_IGNORE			= Arrays.asList("user/loginWithCode", "user/forgotPassword", "user/forgotPasswordValidation", "user/resetPassword");
	public static List<String>	PROFILES_TO_IGNORE		= Arrays.asList("local", "unencript_prod", "unencript_staging");

	public static Date getCurrentDate() {
		return new Date();
	}

	public static MediaType getMediaTypeOfFile(String extension) {

		MediaType mediaType = null;
		switch (extension) {
		case "pdf":
			mediaType = MediaType.parseMediaType(ExtensionEnum.pdf.getValue());
			break;
		case "doc":
			mediaType = MediaType.parseMediaType(ExtensionEnum.doc.getValue());
			break;
		case "docx":
			mediaType = MediaType.parseMediaType(ExtensionEnum.docx.getValue());
			break;
		case "txt":
			mediaType = MediaType.parseMediaType(ExtensionEnum.txt.getValue());
			break;
		case "odt":
			mediaType = MediaType.parseMediaType(ExtensionEnum.odt.getValue());
			break;
		case "xls":
			mediaType = MediaType.parseMediaType(ExtensionEnum.xls.getValue());
			break;
		case "xlsx":
			mediaType = MediaType.parseMediaType(ExtensionEnum.xlsx.getValue());
			break;
		case "ppt":
			mediaType = MediaType.parseMediaType(ExtensionEnum.ppt.getValue());
			break;
		case "jpeg":
			mediaType = MediaType.parseMediaType(ExtensionEnum.jpeg.getValue());
			break;
		case "jpg":
			mediaType = MediaType.parseMediaType(ExtensionEnum.jpeg.getValue());
			break;
		case "png":
			mediaType = MediaType.parseMediaType(ExtensionEnum.png.getValue());
			break;
		case "gif":
			mediaType = MediaType.parseMediaType(ExtensionEnum.gif.getValue());
			break;
		case "mp4":
			mediaType = MediaType.parseMediaType(ExtensionEnum.mp4.getValue());
			break;
		case "avi":
			mediaType = MediaType.parseMediaType(ExtensionEnum.avi.getValue());
			break;
		case "flv":
			mediaType = MediaType.parseMediaType(ExtensionEnum.flv.getValue());
			break;
		case ".mpeg":
			mediaType = MediaType.parseMediaType(ExtensionEnum.mpeg.getValue());
			break;
		case "3gp":
			mediaType = MediaType.parseMediaType(ExtensionEnum.gp.getValue());
			break;
		case "3g2":
			mediaType = MediaType.parseMediaType(ExtensionEnum.g2.getValue());
			break;
		case "ogv":
			mediaType = MediaType.parseMediaType(ExtensionEnum.ogv.getValue());
			break;
		default:
			break;
		}
		return mediaType;
	}

	public static String getContentType(String extension) {

		String mediaType = null;
		switch (extension) {
		case "pdf":
			mediaType = ExtensionEnum.pdf.getValue();
			break;
		case "doc":
			mediaType = ExtensionEnum.doc.getValue();
			break;
		case "docx":
			mediaType = ExtensionEnum.docx.getValue();
			break;
		case "txt":
			mediaType = ExtensionEnum.txt.getValue();
			break;
		case "odt":
			mediaType = ExtensionEnum.odt.getValue();
			break;
		case "xls":
			mediaType = ExtensionEnum.xls.getValue();
			break;
		case "xlsx":
			mediaType = ExtensionEnum.xlsx.getValue();
			break;
		case "ppt":
			mediaType = ExtensionEnum.ppt.getValue();
			break;
		case "jpeg":
			mediaType = ExtensionEnum.jpeg.getValue();
			break;
		case "jpg":
			mediaType = ExtensionEnum.jpeg.getValue();
			break;
		case "png":
			mediaType = ExtensionEnum.png.getValue();
			break;
		case "gif":
			mediaType = ExtensionEnum.gif.getValue();
			break;
		case "mp4":
			mediaType = ExtensionEnum.mp4.getValue();
			break;
		case "avi":
			mediaType = ExtensionEnum.avi.getValue();
			break;
		case "flv":
			mediaType = ExtensionEnum.flv.getValue();
			break;
		case ".mpeg":
			mediaType = ExtensionEnum.mpeg.getValue();
			break;
		case "3gp":
			mediaType = ExtensionEnum.gp.getValue();
			break;
		case "3g2":
			mediaType = ExtensionEnum.g2.getValue();
			break;
		case "ogv":
			mediaType = ExtensionEnum.ogv.getValue();
			break;
		default:
			break;
		}
		return mediaType;
	}

	public static String getContentTypeExtension(String extension) {
		String contentTypeFile = null;

		for (ExtensionEnum enum1 : ExtensionEnum.values()) {
			if (extension.equals(enum1.toString())) {
				contentTypeFile = enum1.getValue();
				break;
			}
		}
		return contentTypeFile;
	}

	public static String getExtensionContentType(String contentType) {
		String extension = null;
		for (ExtensionEnum enum1 : ExtensionEnum.values()) {
			if (contentType.equals(enum1.getValue())) {
				extension = enum1.toString();
				break;
			}
		}
		return extension;
	}

	public static boolean areEquals(Object obj1, Object obj2) {
		return (Objects.equals(obj1, obj2));
	}
	public static <T extends Comparable<T>, Object> boolean areEquals(T obj1, T obj2) {
		return (obj1 == null ? obj2 == null : obj1.equals(obj2));
	}
	public static boolean areNotEquals(Object obj1, Object obj2) {
		return !areEquals(obj1, obj2);
	}
	public static <T extends Comparable<T>, Object> boolean areNotEquals(T obj1, T obj2) {
		return !(areEquals(obj1, obj2));
	}

	private static String getStringImage(File file) {
		try {
			FileInputStream fin        = new FileInputStream(file);
			byte[]          imageBytes = new byte[(int) file.length()];
			fin.read(imageBytes, 0, imageBytes.length);
			fin.close();
			return org.apache.tomcat.util.codec.binary.Base64.encodeBase64String(imageBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String convertToBase64(String pathFichier) {
		File file = new File(Thread.currentThread().getContextClassLoader().getResource(pathFichier).getFile());
		return getStringImage(file);
	}

	public static BigDecimal calculRO(long value1, long value2) {
		double     variation = 0;
		BigDecimal result    = new BigDecimal(0);
		if (value1 != 0L && value2 != 0L) {
			variation = ((double) (value1 * 100)) / value2;
			BigDecimal bigDecimal       = new BigDecimal(variation);
			BigDecimal roundedWithScale = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
			result = roundedWithScale;
		}
		if (value2 == 0L) {
			result = new BigDecimal(0);
		}

		return result;
	}


	public static Calendar anneePrecedente(Calendar calendar) throws ParseException {
		Calendar result = calendar;
		result.add(Calendar.YEAR, -1);
		return result;
	}

	public static Calendar moisPrecedent(Calendar calendar) throws ParseException {
		Calendar result = calendar;
		result.add(Calendar.MONTH, -1);
		return result;
	}


	public static ParameterDto semainePrecedente(ParameterDto parameter, int nombre) throws ParseException {

		ParameterDto parameterDtoRetour = new ParameterDto();
		Calendar     startDateCalendar  = Calendar.getInstance(Locale.FRENCH);
		Calendar     endDateCalendar    = Calendar.getInstance(Locale.FRENCH);

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		if (notBlank(parameter.getStartDate()) && notBlank(parameter.getEndDate())) {
			startDateCalendar.setTime(dateFormat.parse(parameter.getEndDate()));
			endDateCalendar.setTime(dateFormat.parse(parameter.getEndDate()));

			startDateCalendar.add(Calendar.DAY_OF_YEAR, -(6 + nombre));

		} else {
			endDateCalendar.add(Calendar.DAY_OF_YEAR, -(nombre));
			startDateCalendar.add(Calendar.DAY_OF_YEAR, -(6 + nombre));

		}

		parameterDtoRetour.setStartDate(dateFormat.format(startDateCalendar.getTime()));
		parameterDtoRetour.setEndDate(dateFormat.format(endDateCalendar.getTime()));

		return parameterDtoRetour;
	}

	public static Date getFirstDayOfQuarter(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) / 3 * 3);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	public static Date getLastDayOfQuarter(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) / 3 * 3 + 2);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	public static BigDecimal calculEvoluation(Double value1, Double value2) {
		double variation = 0;

		BigDecimal bigDecimal1       = new BigDecimal(value1);
		BigDecimal roundedWithScale1 = bigDecimal1.setScale(2, BigDecimal.ROUND_HALF_UP);

		BigDecimal bigDecimal2       = new BigDecimal(value2);
		BigDecimal roundedWithScale2 = bigDecimal2.setScale(2, BigDecimal.ROUND_HALF_UP);

		BigDecimal result = new BigDecimal(0);
		if (roundedWithScale1.doubleValue() != 0.00 && roundedWithScale2.doubleValue() != 0.00) {
			variation = (((double) (value1 / value2)) - 1) * 100;
			BigDecimal bigDecimal       = new BigDecimal(variation);
			BigDecimal roundedWithScale = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
			result = roundedWithScale;
		}
		if (roundedWithScale2.doubleValue() == 0.00) {
			result = new BigDecimal(0);
		}

		return result;
	}

	public static BigDecimal calculEvoluation(Long value1, Long value2) {
		double variation = 0;

		BigDecimal bigDecimal1       = new BigDecimal(value1);
		BigDecimal roundedWithScale1 = bigDecimal1.setScale(2, BigDecimal.ROUND_HALF_UP);

		BigDecimal bigDecimal2       = new BigDecimal(value2);
		BigDecimal roundedWithScale2 = bigDecimal2.setScale(2, BigDecimal.ROUND_HALF_UP);

		BigDecimal result = new BigDecimal(0);
		if (roundedWithScale1.doubleValue() != 0.00 && roundedWithScale2.doubleValue() != 0.00) {
			variation = (((value1 / (double) value2)) - 1) * 100;
			BigDecimal bigDecimal       = new BigDecimal(variation);
			BigDecimal roundedWithScale = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
			result = roundedWithScale;
		}
		if (roundedWithScale2.doubleValue() == 0.00) {
			result = new BigDecimal(0);
		}

		return result;
	}


	public static BigDecimal calculDivision(Long value1, Long value2) {
		double variation = 0;

		BigDecimal bigDecimal1       = new BigDecimal(value1);
		BigDecimal roundedWithScale1 = bigDecimal1.setScale(2, BigDecimal.ROUND_HALF_UP);

		BigDecimal bigDecimal2       = new BigDecimal(value2);
		BigDecimal roundedWithScale2 = bigDecimal2.setScale(2, BigDecimal.ROUND_HALF_UP);

		BigDecimal result = new BigDecimal(0);
		if (roundedWithScale1.doubleValue() != 0.00 && roundedWithScale2.doubleValue() != 0.00) {
			variation = ((double) (value1 / value2));
			BigDecimal bigDecimal       = new BigDecimal(variation);
			BigDecimal roundedWithScale = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
			result = roundedWithScale;
		}
		if (roundedWithScale2.doubleValue() == 0.00) {
			result = new BigDecimal(0);
		}

		return result;
	}

	public static BigDecimal calculDivision(double value1, double value2) {
		double variation = 0;

		BigDecimal bigDecimal1       = new BigDecimal(value1);
		BigDecimal roundedWithScale1 = bigDecimal1.setScale(2, BigDecimal.ROUND_HALF_UP);

		BigDecimal bigDecimal2       = new BigDecimal(value2);
		BigDecimal roundedWithScale2 = bigDecimal2.setScale(2, BigDecimal.ROUND_HALF_UP);

		BigDecimal result = new BigDecimal(0);
		if (roundedWithScale1.doubleValue() != 0.00 && roundedWithScale2.doubleValue() != 0.00) {
			variation = ((double) (value1 / value2));
			BigDecimal bigDecimal       = new BigDecimal(variation);
			BigDecimal roundedWithScale = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
			result = roundedWithScale;
		}
		if (roundedWithScale2.doubleValue() == 0.00) {
			result = new BigDecimal(0);
		}

		return result;
	}

	public static String getQuatreCaractAnnee(String date) throws ParseException {
		String result = "";

		Calendar         calendar          = Calendar.getInstance();
		SimpleDateFormat dateFormatMois    = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dateFormatMoisNew = new SimpleDateFormat("yyyy");
		calendar.setTime(dateFormatMois.parse(date));
		result = dateFormatMoisNew.format(calendar.getTime()).toLowerCase();

		return result;
	}

	public static String getQuatreCaractAnneeNew(String date) throws ParseException {
		String result = "";

		Calendar         calendar          = Calendar.getInstance();
		SimpleDateFormat dateFormatMois    = new SimpleDateFormat("MM/yyyy");
		SimpleDateFormat dateFormatMoisNew = new SimpleDateFormat("yyyy");
		calendar.setTime(dateFormatMois.parse(date));
		result = dateFormatMoisNew.format(calendar.getTime()).toLowerCase();

		return result;
	}

	public static String getDateAnneecFormat(String date) throws ParseException {
		String result = "";

		Calendar         calendar        = Calendar.getInstance();
		SimpleDateFormat dateFormat      = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dateFormafinale = new SimpleDateFormat("MM/yyyy");
		calendar.setTime(dateFormat.parse(date));
		result = dateFormafinale.format(calendar.getTime());

		return result;

	}


	/**
	 * 
	 * @param request
	 * @return
	 */
	public static String getClientIp(HttpServletRequest request) {
		for (String header : IP_HEADER_CANDIDATES) {
			String ip = request.getHeader(header);
			if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
				return ip;
			}
		}
		return request.getRemoteAddr();
	}

	public static String combinaisonString() {
		String lettres = "";
		try {
			Random random;
			for (int i = 0; i < 10; i++) {
				random = new Random();
				int rn = random.nextInt(35 - 0 + 1) + 0;
				lettres += listeBase.get(rn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lettres;
	}

	public static String formatDate(String date, String initDateFormat, String endDateFormat) throws ParseException {
		if (!notBlank(date)) {
			return "";
		}
		Date initDate = new SimpleDateFormat(initDateFormat).parse(date);
		SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
		String parsedDate = formatter.format(initDate);

		return parsedDate;
	}

	public static Date asDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date asDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static LocalDate asLocalDate(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static LocalDateTime asLocalDateTime(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public static int duration(Date startDate, Date endDate) {
		long duration = ChronoUnit.DAYS.between(asLocalDate(startDate), asLocalDate(endDate));
		return Integer.parseInt(String.valueOf(duration + 1));
	}

	public static int duration(LocalDate startLocalDate, LocalDate endLocalDate) {
		long duration = ChronoUnit.DAYS.between(startLocalDate, endLocalDate);
		return Integer.parseInt(String.valueOf(duration + 1));
	}
	
	/**
	 * Check if a String given is an Integer.
	 *
	 * @param s
	 * @return isValidInteger
	 *
	 */
	public static boolean isInteger(String s) {
		boolean isValidInteger = false;
		try {
			Integer.parseInt(s);

			// s is a valid integer
			isValidInteger = true;
		} catch (NumberFormatException ex) {
			// s is not an integer
		}

		return isValidInteger;
	}

	public static String generateCodeOld() {
		String formatted = null;
		formatted = RandomStringUtils.randomAlphanumeric(8).toUpperCase();
		return formatted;
	}

	public static String generateCode() {
		String formatted = null;
		SecureRandom secureRandom = new SecureRandom();
		int num = secureRandom.nextInt(100000000);
		formatted = String.format("%05d", num);
		return formatted;
	}

	public static boolean isTrue(Boolean b) {
		return b != null && b;
	}

	public static boolean isFalse(Boolean b) {
		return !isTrue(b);
	}

	public static boolean isNumeric(String str) {
		try {
			double d = Long.parseLong(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	/**
	 * Check if a Integer given is an String.
	 *
	 * @param i
	 * @return isValidString
	 *
	 */
	public static boolean isString(Integer i) {
		boolean isValidString = true;
		try {
			Integer.parseInt(i + "");

			// i is a valid integer

			isValidString = false;
		} catch (NumberFormatException ex) {
			// i is not an integer
		}

		return isValidString;
	}

	public static boolean isString(Object obj) {
		return obj instanceof String;
	}

	public static boolean isValidEmail(String email) {
		if (isBlank(email)) {
			return false;
		}
		String  regex   = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);

		return matcher.matches();
	}

	public static String encrypt(String str) throws Exception {
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		byte[] hashedBytes = digest.digest(str.getBytes("UTF-8"));

		return convertByteArrayToHexString(hashedBytes);
	}

	public static String sha256(String originalString) throws NoSuchAlgorithmException {
		MessageDigest digest      = MessageDigest.getInstance("SHA-256");
		byte[]        encodedhash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
		StringBuffer  hexString   = new StringBuffer();
		for (int i = 0; i < encodedhash.length; i++) {
			String hex = Integer.toHexString(0xff & encodedhash[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}

	public static boolean isDateValid(String date) {
		try {
			String simpleDateFormat = "dd/MM/yyyy";

			if (date.contains("-"))
				simpleDateFormat = "dd-MM-yyyy";
			else if (date.contains("/"))
				simpleDateFormat = "dd/MM/yyyy";
			else
				return false;

			DateFormat df = new SimpleDateFormat(simpleDateFormat);
			df.setLenient(false);
			df.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public static String GenerateValueKey(String code) {
		String result = null;
		// String prefix = prefixe;
		String suffix = null;
		String middle = null;
		String separator = "-";
		final String defaut = "000001";
		try {

			SimpleDateFormat dt = new SimpleDateFormat("yy-MM-dd-ss");
			String _date = dt.format(new Date());
			String[] spltter = _date.split(separator);
			middle = spltter[0] + spltter[1] + spltter[2] + spltter[3];
			if (code != null) {
				// Splitter le code pour recuperer les parties
				// String[] parts = code(separator);
				String part = code.substring(1);
				System.out.println("part" + part);

				if (part != null) {
					int cpt = new Integer(part);
					cpt++;

					String _nn = String.valueOf(cpt);

					switch (_nn.length()) {
						case 1:
							suffix = "00000" + _nn;
							break;
						case 2:
							suffix = "0000" + _nn;
							break;
						case 3:
							suffix = "000" + _nn;
							break;
						case 4:
							suffix = "00" + _nn;
							break;
						case 5:
							suffix = "0" + _nn;
							break;
						default:
							suffix = _nn;
							break;
					}
					// result = prefix + separator + middle + separator +
					// suffix;
					result = middle + separator + suffix;
				}
			} else {
				// result = prefix + separator + middle + separator + defaut;
				result = middle + separator + defaut;
			}
		} catch (Exception e) {

		}
		return result;
	}

	public static Integer getAge(Date dateNaissance) throws ParseException, Exception {
		Integer annee = 0;

		if (dateNaissance == null) {
			annee = 0;
		}
		Calendar birth = new GregorianCalendar();
		birth.setTime(dateNaissance);
		Calendar now = new GregorianCalendar();
		now.setTime(new Date());
		int adjust = 0;
		if (now.get(Calendar.DAY_OF_YEAR) - birth.get(Calendar.DAY_OF_YEAR) < 0) {
			adjust = -1;
		}
		annee = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR) + adjust;
		return annee;
	}

	public static Boolean AvailableCode(String code) {
		if (code == null || code.isEmpty()) {
			return false;
		}
		Locale local = new Locale(code, "");
		return LocaleUtils.isAvailableLocale(local);

	}

	public static String normalizeFileName(String fileName) {
		String fileNormalize = null;
		fileNormalize = fileName.trim().replaceAll("\\s+", "_");
		fileNormalize = fileNormalize.replace("'", "");
		fileNormalize = Normalizer.normalize(fileNormalize, Normalizer.Form.NFD);
		fileNormalize = fileNormalize.replaceAll("[^\\p{ASCII}]", "");

		return fileNormalize;
	}

	private static String convertByteArrayToHexString(byte[] arrayBytes) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < arrayBytes.length; i++) {
			stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return stringBuffer.toString();
	}

	public static SimpleDateFormat findDateFormat(String date) {
		SimpleDateFormat simpleDateFormat = null;
		String regex_dd_MM_yyyy = "\\A0?(?:3[01]|[12][0-9]|[1-9])[/.-]0?(?:1[0-2]|[1-9])[/.-][0-9]{4}\\z";

		if (date.matches(regex_dd_MM_yyyy))
			if (date.contains("-"))
				simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
			else if (date.contains("/"))
				simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

		return simpleDateFormat;
	}

	/**
	 * @return Permet de retourner la date courante du système
	 *
	 */
	public static String getCurrentLocalDateTimeStamp() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	/**
	 * @param l
	 *            liste de vérification de doublons
	 * @return retourne le nombre de doublon trouvé
	 *
	 */
	public static int getDupCount(List<String> l) {
		int cnt = 0;
		HashSet<String> h = new HashSet<>(l);

		for (String token : h) {
			if (Collections.frequency(l, token) > 1)
				cnt++;
		}

		return cnt;
	}

	public static boolean saveImage(String base64String, String nomCompletImage, String extension) throws Exception {

		BufferedImage image = decodeToImage(base64String);

		if (image == null) {

			return false;

		}

		File f = new File(nomCompletImage);

		// write the image

		ImageIO.write(image, extension, f);

		return true;

	}

	public static boolean saveVideo(String base64String, String nomCompletVideo) throws Exception {

		try {

			byte[]           decodedBytes = Base64.getDecoder().decode(base64String);
			File             file2        = new File(nomCompletVideo);
			FileOutputStream os           = new FileOutputStream(file2, true);
			os.write(decodedBytes);
			os.close();

		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

		return true;

	}

	public static BufferedImage decodeToImage(String imageString) throws Exception {

		BufferedImage image = null;

		byte[] imageByte;

		imageByte = Base64.getDecoder().decode(imageString);

		try (ByteArrayInputStream bis = new ByteArrayInputStream(imageByte)) {

			image = ImageIO.read(bis);

		}

		return image;

	}

	public static String encodeToString(BufferedImage image, String type) {

		String imageString = null;

		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try {

			ImageIO.write(image, type, bos);

			byte[] imageBytes = bos.toByteArray();

			imageString = new String(Base64.getEncoder().encode(imageBytes));

			bos.close();

		} catch (IOException e) {

			e.printStackTrace();

		}

		return imageString;

	}

	public static String convertFileToBase64(String pathFichier) {
		File originalFile = new File(pathFichier);
		String encodedBase64 = null;
		try {
			FileInputStream fileInputStreamReader = new FileInputStream(originalFile);
			byte[] bytes = new byte[(int) originalFile.length()];
			fileInputStreamReader.read(bytes);
			encodedBase64 = new String(Base64.getEncoder().encodeToString((bytes)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return encodedBase64;
	}

	public static String getImageExtension(String str) {
		String extension = "";
		int    i         = str.lastIndexOf('.');
		if (i >= 0) {
			extension = str.substring(i + 1);
			return extension;
		}
		return null;
	}

	public static boolean fileIsImage(String image) {

		String  IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp|jpeg))$)";
		Pattern pattern       = Pattern.compile(IMAGE_PATTERN);
		Matcher matcher       = pattern.matcher(image);

		return matcher.matches();

	}

	public static boolean fileIsVideo(String video) {

		String  IMAGE_PATTERN = "([^\\s]+(\\.(?i)(mp4|avi|camv|dvx|mpeg|mpg|wmv|3gp|mkv))$)";
		Pattern pattern       = Pattern.compile(IMAGE_PATTERN);
		Matcher matcher       = pattern.matcher(video);

		return matcher.matches();

	}

	public static void createDirectory(String chemin) {
		File file = new File(chemin);
		if (!file.exists()) {
			try {
				FileUtils.forceMkdir(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void deleteFolder(String chemin) {
		File file = new File(chemin);
		try {
			if (file.exists() && file.isDirectory()) {
				FileUtils.forceDelete(new File(chemin));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void deleteFile(String chemin) {
		File file = new File(chemin);
		try {
			if (file.exists() && file.getName() != null && !file.getName().isEmpty()) {

				FileUtils.forceDelete(new File(chemin));

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean notBlank(String str) {
		return str != null && !str.isEmpty() && !str.equals("\n") && org.apache.commons.lang3.StringUtils.isNotBlank(str);
	}

	public static boolean blank(String str) {
		return !notBlank(str);
	}

	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0 || str.isEmpty()) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}
	
	/*
	public static boolean notEmpty(List<String> lst) {
		return lst != null && !lst.isEmpty() && lst.stream().noneMatch(s -> s.equals("\n")) && lst.stream().noneMatch(s -> s.equals(null));
	}
	*/

	public static boolean notEmpty(List<String> lst) {
		return lst != null && !lst.isEmpty() && lst.stream().noneMatch(s -> s.equals("\n")) && lst.stream().noneMatch(s -> isBlank(s));
	}

	public static <T> boolean isNotEmpty(List<T> list){
		return (list != null && !list.isEmpty());
	}

	public static <T> boolean isNotEmpty(T[] array){
		return (array != null && array.length > 0);
	}

	public static <T> boolean isEmpty(List<T> list){
		return (list == null || list.isEmpty());
	}
	
	public static <K, V> boolean isEmpty(Map<K, V> map){
		return (map == null || map.isEmpty());
	}

	static public String GetCode(String Value, Map<String, String> Table) {

		for (Entry<String, String> entry : Table.entrySet()) {
			if (entry.getValue().equals(Value)) {
				return entry.getKey();
			}
		}
		return Value;
	}

	public static boolean anObjectFieldsMapAllFieldsToVerify(List<Object> objets, Map<String, Object> fieldsToVerify) {
		for (Object objet : objets) {
			boolean oneObjectMapAllFields = true;
			JSONObject jsonObject = new JSONObject(objet);
			for (Map.Entry<String, Object> entry : fieldsToVerify.entrySet()) {
				// slf4jLogger.info("jsonObject " +jsonObject);
				String key = entry.getKey();
				Object value = entry.getValue();
				try {
					if (!jsonObject.get(key).equals(value)) {
						oneObjectMapAllFields = false;
						break;
					}
				} catch (Exception e) {
					oneObjectMapAllFields = false;
					break;
				}
			}
			if (oneObjectMapAllFields)
				return true;
		}

		return false;
	}

	public static List<Date> getDaysBetweenDates(Date startdate, Date enddate) {
		List<Date> dates    = new ArrayList<Date>();
		Calendar   calendar = new GregorianCalendar();
		calendar.setTime(startdate);

		while (calendar.getTime().before(enddate) || calendar.getTime().equals(enddate)) {
			Date result = calendar.getTime();
			dates.add(result);
			calendar.add(Calendar.DATE, 1);
		}
		return dates;
	}

	public static List<Date> getDaysBetweenDates(Date startdate, Date enddate, SimpleDateFormat dateFormat) throws ParseException {
		List<Date> dates    = new ArrayList<Date>();
		
		startdate = dateFormat.parse(dateFormat.format(startdate));
		enddate = dateFormat.parse(dateFormat.format(enddate));
		
		Calendar   calendar = new GregorianCalendar();
		calendar.setTime(startdate);

		while (calendar.getTime().before(enddate) || calendar.getTime().equals(enddate)) {
			Date result = calendar.getTime();
			dates.add(result);
			calendar.add(getCalendarField(dateFormat.toPattern()), 1);
		}
		return dates;
	}

	public static List<String> getIndexBetweenDates(Date startdate, Date enddate, String root) {
		List<Date>       dates     = getDaysBetweenDates(startdate, enddate);
		List<String>     str_dates = new ArrayList<String>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat formatter_new = new SimpleDateFormat("yyyyMM");


		dates.forEach(d -> {
			if(!root.contains("omer_vente_kit_") && !root.contains("omer_ro_kits_vendus_") && !root.contains("airtime_commission_") && !root.contains("objectifs_")){
				str_dates.add(root + formatter.format(d));
				//slf4jLogger.info("index  ->  " + root + formatter.format(d));
			}
			else{
				str_dates.add(root + formatter_new.format(d));
				//slf4jLogger.info("index  ->  " + root + formatter_new.format(d));
			}
		});

		return str_dates;
	}

	public static List<String> getIndexBetweenDates(Date startdate, Date enddate, String root, SimpleDateFormat formatter) throws ParseException {
		List<Date>       dates     = getDaysBetweenDates(startdate, enddate, formatter);
		List<String>     str_dates = new ArrayList<String>();
		dates.forEach(d -> {
			str_dates.add(root + formatter.format(d));
		});
		return str_dates;
	}

	public static List<String> getIndexBetweenDatesV2(Date startdate, Date enddate, String root) {
		List<Date>       dates     = getDaysBetweenDates(startdate, enddate);
		List<String>     str_dates = new ArrayList<String>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");

		dates.forEach(d -> {
			str_dates.add(root + formatter.format(d));
		});

		return str_dates;
	}

	public static List<String> getIndexBetweenDatesMois(Date startdate, Date enddate, String root) {
		List<Date>       dates     = getDaysBetweenDates(startdate, enddate);
		List<String>     str_dates = new ArrayList<String>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");

		dates.forEach(d -> {
			str_dates.add(root + formatter.format(d));
		});

		return str_dates;
	}

	public static String[] getIndexesArray(String start, String end, SimpleDateFormat dateFormat, String root, SimpleDateFormat indexDateFormatter) throws Exception {
		if(dateFormat == null) {
			dateFormat = _dateFormat;
		}
		
		Date d1 = dateFormat.parse(start);
		Date d2 = dateFormat.parse(end);

		List<String> indexes = Utilities.getIndexBetweenDates(d1, d2, root, indexDateFormatter);
		String[] indexesArr = new String[indexes.size()];
		return indexes.toArray(indexesArr);
	}

	public static String[] getIndexesArray(String start, String end, SimpleDateFormat dateFormat, String root) throws ParseException {

		if(dateFormat == null) {
			dateFormat = _dateFormat;
		}

		Date d1 = dateFormat.parse(start);
		Date d2 = dateFormat.parse(end);
		
		List<String> indexes = getIndexBetweenDates(d1, d2, root);
		String[] indexesArr = new String[indexes.size()];
		return indexes.toArray(indexesArr);
	}

	public static String[] getIndexesArrayV2(String start, String end, SimpleDateFormat dateFormat, String root) throws ParseException {
		if(dateFormat == null) {
			dateFormat = _dateFormat;
		}

		Date d1 = dateFormat.parse(start);
		Date d2 = dateFormat.parse(end);
		
		List<String> indexes = getIndexBetweenDatesV2(d1, d2, root);
		String[] indexesArr = new String[indexes.size()];
		return indexes.toArray(indexesArr);
	}

	public static String[] getIndexesArrayMois(String start, String end, SimpleDateFormat dateFormat, String root) throws ParseException {
		if(dateFormat == null) {
			dateFormat = _dateFormat;
		}

		Date d1 = dateFormat.parse(start);
		Date d2 = dateFormat.parse(end);
		
		List<String> indexes = getIndexBetweenDatesMois(d1, d2, root);
		String[] indexesArr = new String[indexes.size()];
		return indexes.toArray(indexesArr);
	}

	public static int compareToDate(String first_date, String second_date, SimpleDateFormat dateFormat) throws ParseException {
		Date first  = dateFormat.parse(first_date);
		Date second = dateFormat.parse(second_date);
		return first.compareTo(second);
	}

	public static String getDateJour(String date) throws ParseException {
		String result = "";

		// TimeZone timeZone = TimeZone.getTimeZone("UTC");
		// Calendar calendar = Calendar.getInstance(timeZone);
		Calendar         calendar          = Calendar.getInstance();
		SimpleDateFormat dateFormatMois    = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat dateFormatMoisNew = new SimpleDateFormat("dd/MM/yyyy");
		calendar.setTime(dateFormatMois.parse(date));

		result = dateFormatMoisNew.format(calendar.getTime()).toLowerCase();

		return result;
	}

	public static String getMoisFormatFinale(String date) throws ParseException {
		String result = "";

		Calendar         calendar          = Calendar.getInstance();
		SimpleDateFormat dateFormatMois    = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat dateFormatMoisNew = new SimpleDateFormat("MM/yyyy");
		calendar.setTime(dateFormatMois.parse(date));
		result = dateFormatMoisNew.format(calendar.getTime()).toLowerCase();

		return result;

	}

	public static String getDernierJourMoisFormatFinale(String date) throws ParseException {
		String result = "";

		Calendar calendar = Calendar.getInstance();
		//SimpleDateFormat dateFormatMois = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat dateFormatMoisNew = new SimpleDateFormat("MM/yyyy");
		calendar.setTime(dateFormatMoisNew.parse(date));

		int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		result = String.valueOf(lastDay);

		return result;

	}

	public static String getAnneeJour(String date) throws ParseException {
		String result = "";

		// TimeZone timeZone = TimeZone.getTimeZone("UTC");
		// Calendar calendar = Calendar.getInstance(timeZone);
		Calendar         calendar          = Calendar.getInstance();
		SimpleDateFormat dateFormatMois    = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dateFormatMoisNew = new SimpleDateFormat("yyyy");
		calendar.setTime(dateFormatMois.parse(date));

		result = dateFormatMoisNew.format(calendar.getTime()).toLowerCase();

		return result;
	}

	public static BigDecimal calculTOT(long value1, long value2) {
		double     variation = 0;
		BigDecimal result    = new BigDecimal(0);
		if (value1 != 0L && value2 != 0L) {
			//	variation = ((double) (value1 * 100)) / value2;
			double v1 = 0.0;
			double v2 = 0.0;
			v1 = (double) value1;
			v2 = (double) value2;
			variation = (((double) (v1 / v2)) - 1) * 100;
			BigDecimal bigDecimal       = new BigDecimal(variation);
			BigDecimal roundedWithScale = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
			result = roundedWithScale;
		}
		if (value2 == 0L) {
			result = new BigDecimal(0);
		}

		return result;
	}

	public static String getDateMoisPrec(String date) throws ParseException {
		String result = "";

		Calendar         calendar   = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		calendar.setTime(dateFormat.parse(date));

		calendar.add(Calendar.MONTH, -1);
		result = dateFormat.format(calendar.getTime()).toLowerCase();

		return result;

	}

	public static String getDateSemaineNew(String date) throws ParseException {
		String result = "";

		// TimeZone timeZone = TimeZone.getTimeZone("UTC");
		// Calendar calendar = Calendar.getInstance(timeZone);
		Calendar         calendar       = Calendar.getInstance();
		SimpleDateFormat dateFormatMois = new SimpleDateFormat("ww-yyyy");
		//SimpleDateFormat dateFormatMoisNew = new SimpleDateFormat("yyyy-ww");
		SimpleDateFormat dateFormatMoisNew = new SimpleDateFormat("yyyy-ww");
		calendar.setTime(dateFormatMois.parse(date));

		result = dateFormatMoisNew.format(calendar.getTime()).toLowerCase();

		return result;
	}

	public static String getDateSemaine(String date) throws ParseException {
		String result = "";

		// TimeZone timeZone = TimeZone.getTimeZone("UTC");
		// Calendar calendar = Calendar.getInstance(timeZone);
		Calendar         calendar       = Calendar.getInstance();
		SimpleDateFormat dateFormatMois = new SimpleDateFormat("yyyy-ww");
		//SimpleDateFormat dateFormatMoisNew = new SimpleDateFormat("yyyy-ww");
		SimpleDateFormat dateFormatMoisNew = new SimpleDateFormat("ww-yyyy");
		calendar.setTime(dateFormatMois.parse(date));

		result = dateFormatMoisNew.format(calendar.getTime()).toLowerCase();

		return result;
	}

	public static String getDateMois(String date) throws ParseException {
		String result = "";

		// TimeZone timeZone = TimeZone.getTimeZone("UTC");
		// Calendar calendar = Calendar.getInstance(timeZone);
		Calendar         calendar       = Calendar.getInstance(Locale.FRANCE);
		SimpleDateFormat dateFormatMois = new SimpleDateFormat("yyyy-MM");
		//SimpleDateFormat dateFormatMoisNew = new SimpleDateFormat("MMMMMM yyyy");
		SimpleDateFormat dateFormatMoisNew = new SimpleDateFormat("MM/yyyy");
		calendar.setTime(dateFormatMois.parse(date));

		result = dateFormatMoisNew.format(calendar.getTime()).toLowerCase();
		slf4jLogger.info("mois  envoyé : " + date);
		slf4jLogger.info("resultat  : " + result);

		return result;
	}

	public static String getDateAnneePrec(String date) throws ParseException {
		String result = "";

		Calendar         calendar   = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		calendar.setTime(dateFormat.parse(date));

		calendar.add(Calendar.YEAR, -1);

		result = dateFormat.format(calendar.getTime());

		return result;

	}

	public static int getCalendarField(String datePattern) {
		if(isBlank(datePattern)) {
			return Calendar.DATE;
		}

		if(Arrays.asList("yyyyMM", "yyyy.MM", "yyyy-MM", "MMyyyy", "MM.yyyy", "MM-yyyy").contains(datePattern)) {
			return Calendar.MONTH;
		}
		if(Arrays.asList("yyyy").contains(datePattern)) {
			return Calendar.YEAR;
		}

		return Calendar.DATE;
	}

	public static byte[] base64ToByte(String data) throws Exception {
		return org.apache.commons.codec.binary.Base64.decodeBase64(data);
	}

	public static String byteToBase64(byte[] data) {
		return org.apache.commons.codec.binary.Base64.encodeBase64String(data);
	}

	public static String byteToBase64UrlSafe(byte[] data) {
		return org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString(data);
	}

	public static void deleteFileOnSever(List<String> fileNameList, ParamsUtils paramsUtils) {
		if (fileNameList != null && !fileNameList.isEmpty()) {
			for (String fileName : fileNameList) {
				// Repertoire où se trouve le fichier
				if (fileName != null && fileName.contains(".")) {
					String file[] = fileName.split("\\.");
					if (file.length > 1) {
						String fileExtension = file[file.length - 1];
						String fullFileName  = file[0];
						for (int k = 1; k < file.length - 1; k++) {
							fullFileName += "." + file[k];
						}
						file = fullFileName.split("/");
						fileName = file[file.length - 1];

						String filesDirectory = getSuitableFileDirectory(fileExtension, paramsUtils);
						deleteFile(filesDirectory + "/" + fileName);
					}
				}
			}
		}
	}

	public static String getSuitableFileDirectory(String fileExtension, ParamsUtils paramsUtils) {
		String suitableFileDirectory = "";
		/**
		if (fileIsImage("file." + fileExtension)) {
			suitableFileDirectory = paramsUtils.getImageDirectory();
		} else {
			if (fileIsTexteDocument("file." + fileExtension)) {
				suitableFileDirectory = paramsUtils.getTextfileDirectory();
			} else {
				if (fileIsVideo("file." + fileExtension)) {
					suitableFileDirectory = paramsUtils.getVideoDirectory();
				}
			}
		}
		if (suitableFileDirectory == null) {
			suitableFileDirectory = paramsUtils.getOtherfileDirectory();
		}
		 **/
		return String.format("%s%s", paramsUtils.getRootFilesPath(), suitableFileDirectory);
		//return suitableFileDirectory;
	}

	public static boolean fileIsTexteDocument(String textDocument) {

		String  TEXT_DOCUMENT_PATTERN = "([^\\s]+(\\.(?i)(doc|docx|txt|odt|ods|pdf|xls|xlsx|csv))$)";
		Pattern pattern               = Pattern.compile(TEXT_DOCUMENT_PATTERN);
		Matcher matcher               = pattern.matcher(textDocument);
		return matcher.matches();
	}

	public static String addSlash(String path) {
		if (notBlank(path)) {
			if (!path.endsWith("/")) {
				path += "/";
			}
		}
		return path;
	}

	public static String getSuitableFileUrl(String fileName, ParamsUtils paramsUtils) {
		String suitableFileDirectory = "";
		/**String file[]                = fileName.split("\\.");
		if (file.length > 0) {
			String fileExtension = (file.length > 2) ? file[(file.length - 1)] : file[1];
			if (fileIsImage("file." + fileExtension)) {
				suitableFileDirectory = paramsUtils.getImageDirectory();
			} else {
				if (fileIsTexteDocument("file." + fileExtension)) {
					suitableFileDirectory = paramsUtils.getTextfileDirectory();
				} else {
					if (fileIsVideo("file." + fileExtension)) {
						suitableFileDirectory = paramsUtils.getVideoDirectory();
					}
				}
			}
		}
		if (suitableFileDirectory == null) {
			suitableFileDirectory = paramsUtils.getOtherfileDirectory();
		}
		 **/
		return String.format("%s%s%s", paramsUtils.getRootFilesUrl(), suitableFileDirectory, fileName);
	}

	public static String generateAlphanumericCode(Integer nbreCaractere) {
		String formatted = null;
		formatted = RandomStringUtils.randomAlphanumeric(nbreCaractere).toUpperCase();
		return formatted;
	}

	public static Boolean verifierEmail(String email) {
		Pattern emailPattern = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher emailMatcher = emailPattern.matcher(email);
		return emailMatcher.matches();
	}

	public static String randomString(int len) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(alphaNum.charAt(rnd.nextInt(alphaNum.length())));
		return sb.toString();
	}

	public static String randomHexaString(int len) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(hexaAlphabet.charAt(rnd.nextInt(hexaAlphabet.length())));
		return sb.toString();
	}

	public static String getFilePath(String pathFichier) {
		//slf4jLogger.info("--pathFichier--" + pathFichier);
		File file = null;
		try {
			file = new ClassPathResource(pathFichier).getFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return file.getAbsolutePath();
	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		Map<Object,Boolean> seen = new ConcurrentHashMap<>();
		return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

	public static boolean saveFile(String base64String, String nomCompletVideo) throws Exception {

		try {

			byte[] decodedBytes = Base64.getDecoder().decode(base64String);
			File file2 = new File(nomCompletVideo);
			FileOutputStream os = new FileOutputStream(file2, true);
			os.write(decodedBytes);
			os.close();

		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

		return true;

	}

	public static String saveFile(_FileDto dataFile, ParamsUtils paramsUtils) throws IOException, Exception {
		String filePath = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss_SSSSS");
		String fileName =null;
		if(dataFile != null) {
			String fileDirectory = null;
			if (notBlank(dataFile.getFileName()) && notBlank(dataFile.getFileBase64()) && notBlank(dataFile.getExtension())) 
			{
				fileName = dataFile.getFileName();
				if (fileName.contains("/")) {
					String[] fileNames = dataFile.getFileName().split("/");
					fileName = fileNames[fileNames.length - 1];
				}
				fileName = normalizeFileName(fileName)+"_"+sdf.format(new Date())+"."+dataFile.getExtension();

				//S'assurer que l'extension est bonne
				if (!fileIsImage(fileName) && !fileIsTexteDocument(fileName) && !fileIsVideo(fileName)) {
					throw new IOException("L'extension '"+dataFile.getExtension()+"' n'est pas prise en compte !");
				}

				// Repertoire où je depose mon fichier
				String filesDirectory = getSuitableFileDirectory(dataFile.getExtension(), paramsUtils);
				createDirectory(filesDirectory);
				if (!filesDirectory.endsWith("/")) {
					filesDirectory += "/";
				}
				fileDirectory=filesDirectory+fileName;

				//Enregistrement du fichier
				boolean succes = false;
				succes = saveFile(dataFile.getFileBase64(), fileDirectory);

				//          if(fileIsImage(fileName)) {
				//            succes = saveImage(dataFile.getFileBase64(), fileDirectory, dataFile.getExtension());
				//          }
				//          else {
				//            if(fileIsTexteDocument(fileName)) {
				//              BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(fileDirectory)));
				//              stream.write(base64ToByte(dataFile.getFileBase64()));
				//              stream.close();
				//            }
				//            else {
				//              if(fileIsVideo(fileName)) {
				//                succes = saveVideo(dataFile.getFileBase64(), fileDirectory);
				//              }
				//            }
				//          }

				if (!succes) {
					throw new Exception("Echec de l'enregistrement du fichier '"+fileDirectory+"' !");
				}
				filePath = fileDirectory;
			}
		}    

		return fileName;
		//return filePath;
	}

	public static String findDateFormatByParsing(String date) {
		if(blank(date)) {
			return null;
		}

		List<String> datasPatterns = new ArrayList<String>();

		datasPatterns.addAll(Arrays.asList("dd/MM/yyyy", "dd-MM-yyyy", "dd.MM.yyyy", "ddMMyyyy"));
		datasPatterns.addAll(Arrays.asList("dd/MM/yyyy HH", "dd-MM-yyyy HH", "dd.MM.yyyy HH", "ddMMyyyy HH"));
		datasPatterns.addAll(Arrays.asList("dd/MM/yyyy HH:mm", "dd-MM-yyyy HH:mm", "dd.MM.yyyy HH:mm", "ddMMyyyy HH:mm"));
		datasPatterns.addAll(Arrays.asList("dd/MM/yyyy HH:mm:ss", "dd-MM-yyyy HH:mm:ss", "dd.MM.yyyy HH:mm:ss", "ddMMyyyy HH:mm:ss"));
		datasPatterns.addAll(Arrays.asList("dd/MM/yyyy HH:mm:ss.SSS", "dd-MM-yyyy HH:mm:ss.SSS", "dd.MM.yyyy HH:mm:ss.SSS", "ddMMyyyy HH:mm:ss.SSS"));

		datasPatterns.addAll(Arrays.asList("yyyy/MM/dd", "yyyy-MM-dd", "yyyy.MM.dd", "yyyyMMdd"));
		datasPatterns.addAll(Arrays.asList("yyyy/MM/dd HH", "yyyy-MM-dd HH", "yyyy.MM.dd HH", "yyyyMMdd HH"));
		datasPatterns.addAll(Arrays.asList("yyyy/MM/dd HH:mm", "yyyy-MM-dd HH:mm", "yyyy.MM.dd HH:mm", "yyyyMMdd HH:mm"));
		datasPatterns.addAll(Arrays.asList("yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss", "yyyy.MM.dd HH:mm:ss", "yyyyMMdd HH:mm:ss"));
		datasPatterns.addAll(Arrays.asList("yyyy/MM/dd HH:mm:ss.SSS", "yyyy-MM-dd HH:mm:ss.SSS", "yyyy.MM.dd HH:mm:ss.SSS", "yyyyMMdd HH:mm:ss.SSS"));

		datasPatterns.addAll(Arrays.asList("dd/MM", "dd-MM", "dd.MM", "ddMM"));
		datasPatterns.addAll(Arrays.asList("MM/yyyy", "MM-yyyy", "MM.yyyy", "MMyyyy"));

		datasPatterns.addAll(Arrays.asList("MM/dd", "MM-dd", "MM.dd", "MMdd"));
		datasPatterns.addAll(Arrays.asList("yyyy/MM", "yyyy-MM", "yyyy.MM", "yyyyMM"));

		datasPatterns.addAll(Arrays.asList("yyyy"));

		datasPatterns.addAll(Arrays.asList("HH", "HH:mm", "HH:mm:ss", "HH:mm:ss.SSS"));

		for (String pattern : datasPatterns) {
			try {
				//					SimpleDateFormat sdf = new SimpleDateFormat(pattern);
				//					sdf.setLenient(false);
				//					sdf.parse(date);

				org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
				formatter.parseDateTime(date);
				return pattern;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return null;
	}

	public static BigDecimal calculTaux(Double value1, Double value2, int newScale,  int roundingMode) {
		double variation = 0;
		BigDecimal result = new BigDecimal(0);
		if (value1 != 0d && value2 != 0d) {
			variation = ((double) (value1 * 100)) / value2;
			BigDecimal bigDecimal = new BigDecimal(variation);
			BigDecimal roundedWithScale = bigDecimal.setScale(newScale, roundingMode);
			result = roundedWithScale;
		}
		if (value2 == 0L) {
			result = new BigDecimal(0);
		}

		return result;
	}

	public static BigDecimal calculTaux(Double value1, Double value2, int newScale) {
		return calculTaux(value1, value2, newScale, BigDecimal.ROUND_HALF_UP);
	}

	public static BigDecimal calculTaux(Double value1, Double value2) {
		return calculTaux(value1, value2, 2);
	}

	public static Double caluclerVariation(Double valueAtH, Double valueAtHPlusX) {
		if(valueAtH.equals(0d)) {
			return Double.NaN;
		}
		return 100.0 * ((valueAtHPlusX/valueAtH) - 1);
	}

	public static String getInterval(String start, String end) {
		String interval = "";
		if(notBlank(start) && notBlank(end)) {
			interval += "["+start+" - "+end+"], ";
		}
		else {
			if(notBlank(start) && blank(end)) {
				interval += "["+start+" ; +∞[, ";
			}
			else {
				if(blank(start) && notBlank(end)) {
					interval += "]-∞ ; "+end+"], ";
				}
				else {
					interval += "]-∞ ; +∞[, ";
				}
			}
		}
		//interval = interval.substring(0, interval.length() - 2);
		return interval;
	}
	
	public static boolean isValidateIvorianPhoneNumber(String phoneNumber){
		String regex = "^(\\(\\+225\\)|\\+225|\\(00225\\)|00225)?(\\s)?[0-9]{2}([ .-]?[0-9]{2}){3}$";
		return (phoneNumber != null && phoneNumber.matches(regex));
	}

	public static String ivorianPhoneNumberToStandardFormat(String phoneNumber){
		String beginRegex = "^(\\(\\+225\\)|\\+225|\\(00225\\)|00225)?";
		String specialCharRegex = "[ .-]?";
		String simplePhoneNumber;

		if(phoneNumber == null)
			return null;

		simplePhoneNumber = phoneNumber.replaceAll(beginRegex, "");
		return simplePhoneNumber.replaceAll(specialCharRegex, "");
	}

//	public static <T> boolean searchParamIsNotEmpty(SearchParam<T> fieldParam) {
//		return fieldParam != null && isNotBlank(fieldParam.getOperator()) && (fieldParam.getStart() != null || fieldParam.getEnd() != null || isNotEmpty(fieldParam.getDatas()) );
//	}

	public static <T> boolean searchParamIsNotEmpty(SearchParam<T> fieldParam) {
		return fieldParam != null && isNotBlank(fieldParam.getOperator()) && OperatorEnum.IS_VALID_OPERATOR(fieldParam.getOperator()) && 
				(
					(OperatorEnum.IS_BETWEEN_OPERATOR(fieldParam.getOperator()) && fieldParam.getStart() != null && isNotBlank(fieldParam.getStart().toString()) && fieldParam.getEnd() != null  && isNotBlank(fieldParam.getEnd().toString()))
						|| 
					(OperatorEnum.IS_IN_OPERATOR(fieldParam.getOperator()) && isNotEmpty(fieldParam.getDatas())) 
						||
					(OperatorEnum.OPERATOR_NOT_NEEDS_ANY_VALUE(fieldParam.getOperator()))
						||
					(OperatorEnum.OPERATOR_NEEDS_FIELD_VALUE(fieldParam.getOperator()))
				);
	}
	
	public static <T> boolean searchParamIsNotEmpty(SearchParam<T> fieldParam, T fieldValue) {
		return fieldParam != null && isNotBlank(fieldParam.getOperator()) && OperatorEnum.IS_VALID_OPERATOR(fieldParam.getOperator()) && 
				(
						(OperatorEnum.IS_BETWEEN_OPERATOR(fieldParam.getOperator()) && fieldParam.getStart() != null && isNotBlank(fieldParam.getStart().toString()) && fieldParam.getEnd() != null  && isNotBlank(fieldParam.getEnd().toString()))
							|| 
						(OperatorEnum.IS_IN_OPERATOR(fieldParam.getOperator()) && isNotEmpty(fieldParam.getDatas())) 
							||
						(OperatorEnum.OPERATOR_NOT_NEEDS_ANY_VALUE(fieldParam.getOperator()))
							||
						(OperatorEnum.OPERATOR_NEEDS_FIELD_VALUE(fieldParam.getOperator()) && fieldValue != null && isNotBlank(fieldValue.toString()))
				);
	}

	public static <T> List<T> paginner(List<T> allItems, Integer index, Integer size) {
		if(isEmpty(allItems)) {
			return null;
		}

		List<T> items = new ArrayList<T>();
		//si une pagination est pécisée, ne prendre que le nombre d'éléments demandés
		if(index != null && index >= 0 && size != null && size >= 0)
		{
			Integer fromIndex = index*size;
			if(fromIndex < allItems.size())
			{
				Integer toIndex = fromIndex + size;
				if(toIndex > allItems.size()) {
					toIndex = allItems.size();
				}
				items.addAll(allItems.subList(fromIndex, toIndex));
			}
		}
		else{
			items.addAll(allItems);
		}

		return items;
	}

	public static String convertSecondToTime(Long seconds) {
		Long heure = seconds / 3600L;
		Long minute = (seconds - heure * 3600L)/60L;
		Long second = seconds % 60L;

		String HH = (heure < 10) ? "000"+heure : (heure < 100) ? "00"+heure : (heure < 1000) ? "0"+heure : heure+"" ;
		String mm = (minute < 10) ? "0"+minute : minute+"";
		String ss = (second < 10) ? "0"+second : second+"";

		return HH + ":" + mm + ":" + ss ;
	}
	
	public static <K extends Comparable<? super K>, V> Map<K, V> sortByKey(Map<K, V> map) {
        List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByKey());

        Map<K, V> result = new LinkedHashMap<>();
        for (Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
	
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
	
	public static String readJsonDefn(String url) throws Exception {
		// implement it the way you like
		StringBuffer bufferJSON = new StringBuffer();

		FileInputStream input = new FileInputStream(new File(url).getAbsolutePath());
		DataInputStream inputStream = new DataInputStream(input);
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

		String line;

		while ((line = br.readLine()) != null) {
			bufferJSON.append(line);
		}
		br.close();
		return bufferJSON.toString();
	}

	public static boolean isValidID(Integer id) {
		return id != null && id > 0;
	}
    public static File getNewFile(String path) {
        return new File(getAbsoluePath(path));
    }

    public static boolean existFile(String path) {
        File file = getNewFile(path);
        return file.exists();
    }

    public static String getAbsoluePath(String path) {
        String fullPath = path;

        if (Thread.currentThread() != null && Thread.currentThread().getContextClassLoader() != null) {
            Object object = Thread.currentThread().getContextClassLoader().getResource(path);
            if (object != null) {
                fullPath = Thread.currentThread().getContextClassLoader().getResource(path).getFile();
            }
        }
        return fullPath;
    }

    public static File stringToFile(String text, String fileName, boolean createFile) {
        File file = new File(fileName);
        try {
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            if (createFile) {
                FileWriter     fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(text);
                bw.close();
            }

        } catch (IOException e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
        return file;
    }

    public static String[] csvToArray(String fileName) throws IOException {
        List<String>   lines = new ArrayList<String>();
        String         currentLine;
        BufferedReader br;
        br = new BufferedReader(new FileReader(fileName));
        while ((currentLine = br.readLine()) != null) {
            lines.add(currentLine);
            System.out.println(currentLine);
        }
        br.close();
        return lines.toArray(new String[]{});
    }

	public static String getStringFromFile(File file) throws Exception {
		StringBuffer buffer = new StringBuffer();

		FileInputStream input = new FileInputStream(file.getAbsolutePath());
		DataInputStream inputStream = new DataInputStream(input);
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

		String line;

		while ((line = br.readLine()) != null) {
			buffer.append(line);
		}
		br.close();
		return buffer.toString();
	}

	public static String removeTrailingZeros(double d, boolean thousandSeparator) {
		String result = "";
		if(thousandSeparator) {
			result =  String.format(Locale.FRANCE, "%,f", d);
		}
		else {
			result = String.valueOf(d);
		}
		return result.replaceAll("[0]*$", "").replaceAll(".$", "");
	}

	public static boolean isValidPhone(String password) {

		boolean result = true;
		Integer longueur = password.length();
		// System.out.println(longueur);
		if (longueur != 10)
			result = false;
		return result;
	}


}
