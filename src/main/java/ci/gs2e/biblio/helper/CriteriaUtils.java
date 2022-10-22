
/*
 * Created on 2022-10-21 ( Time 23:35:15 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2019 Smile backend genarator. All Rights Reserved.
 */

package ci.gs2e.biblio.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import ci.gs2e.biblio.helper.contrat.SearchParam;
import ci.gs2e.biblio.helper.enums.OperatorEnum;


/**
 * 
 * @author Smile backend genarator
 *
 */
public class CriteriaUtils {

	final static String START_SUFFIX = "_start";
	final static String END_SUFFIX   = "_end";

	/**
	 * 
	 * @param listOfQuery
	 * @return
	 */
	public static String getCriteriaByListOfQuery(List<String> listOfQuery) {
		StringBuilder query = new StringBuilder();
		if (!listOfQuery.isEmpty()) {
			for (String q : listOfQuery) {
				query.append(q);
				if (!listOfQuery.get(listOfQuery.size() - 1).equals(q)) {
					query.append(" and ");
				}
			}
		}
		return query.toString();
	}
	

	/**
	 * 
	 * @param <T>
	 * @param datas
	 * @return (d1, d2, ..., dn)
	 */
	public static <T> String getDatasAsSting(List<T> datas) {
		StringBuilder query = new StringBuilder();
		if(Utilities.isNotEmpty(datas)) {
			query.append("(");
			for (T data : datas) {
				query.append(data);
				if (!datas.get(datas.size() - 1).equals(data)) {
					query.append(", ");
				}
			}
			query.append(")");
		}
		
		return query.toString();
	}
	

	

	/**
	 * 
	 * @param <T>
	 * @param datas
	 * @return (d1, d2, ..., dn)
	 * @throws ParseException 
	 */
	public static <T> List<Date> getDatasAsListOfDate(List<T> datas) throws ParseException {
		List<Date> listOfDate = new ArrayList<Date>();
		if(Utilities.isNotEmpty(datas)) {
			for (T data : datas) {
				String dataAsString = (String)data;
				String pattern = Utilities.findDateFormatByParsing(dataAsString);
				if(Utilities.notBlank(pattern)) {
					listOfDate.add((new SimpleDateFormat(pattern)).parse((String)data));
				}
			}
		}
		
		return listOfDate;
	}
	
	
	/**
	 * 
	 * @param <T>
	 * @param datas
	 * @return (d1, d2, ..., dn)
	 * @throws ParseException 
	 */
	public static <T> List<LocalTime> getDatasAsListOfTime(List<T> datas) throws ParseException {
		List<LocalTime> listOfTime = new ArrayList<LocalTime>();
		if(Utilities.isNotEmpty(datas)) {
			for (T data : datas) {
				String dataAsString = (String)data;
				String pattern = Utilities.findDateFormatByParsing(dataAsString);
				if(Utilities.notBlank(pattern)) {
					//LocalDateTime lt = LocalDateTime.parse(dataAsString);
					LocalTime lt = LocalTime.parse(dataAsString);
					listOfTime.add(lt);
				}
			}
		}
		
		return listOfTime;
	}

	/**
	 * 
	 * @param fieldName
	 * @param fieldValue
	 * @param jpqlFieldName
	 * @param fieldType
	 * @param fieldParam
	 * @param params
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> String generateCriteria(String fieldName, Object fieldValue, String jpqlFieldName, String fieldType, SearchParam<T> fieldParam, HashMap<String, Object> params, Integer index,
			Locale locale) throws Exception {
		String req = "";
		String operator = "";
		T start = null;
		T end = null;
		List<T> datas = null;

		if (fieldParam != null) {
			operator = fieldParam.getOperator();
			start = fieldParam.getStart();
			end = fieldParam.getEnd();
			datas = fieldParam.getDatas();
		}
		fieldName += "_" + index;

		if (OperatorEnum.IS_BETWEEN_OPERATOR(operator) && (start == null || Utilities.blank(start.toString()) || end == null || Utilities.blank(end.toString()))) {
			throw new Exception("Field not given: (`start`, `end`)");
		}

		if (OperatorEnum.IS_IN_OPERATOR(operator) && Utilities.isEmpty(datas)) {
			throw new Exception("Field not given: `datas`");
		}
		
		if (OperatorEnum.OPERATOR_NEEDS_FIELD_VALUE(operator) && (fieldValue == null || Utilities.isBlank(fieldValue.toString()))) {
			throw new Exception("Field not given: `"+fieldName+"`");
		}

		switch (fieldType) {
			case "String":
				switch (operator) {
					case OperatorEnum.NOT_EQUAL_1:
					case OperatorEnum.NOT_EQUAL_2:
						req += String.format(" %1$s <> :%2$s ", jpqlFieldName, fieldName);
						break;
					case OperatorEnum.EQUAL:
						req += String.format(" %1$s = :%2$s ", jpqlFieldName, fieldName);
						break;
					case OperatorEnum.START_WTIH:
						req += String.format(" %1$s LIKE :%2$s ", jpqlFieldName, fieldName);
						fieldValue = fieldValue + "%";
						break;
					case OperatorEnum.END_WTIH:
						req += String.format(" %1$s LIKE :%2$s ", jpqlFieldName, fieldName);
						fieldValue = "%" + fieldValue;
						break;
					case OperatorEnum.IN:
						req += String.format(" %1$s IN :%2$s ", jpqlFieldName, fieldName);
						fieldValue = datas;
						break;
					case OperatorEnum.NOT_IN:
						req += String.format(" %1$s NOT IN :%2$s ", jpqlFieldName, fieldName);
						fieldValue = datas;
						break;
					case OperatorEnum.EXISTS:
						req += String.format(" %1$s IS NOT NULL ", jpqlFieldName);
						break;
					case OperatorEnum.NOT_EXISTS:
						req += String.format(" %1$s IS NULL ", jpqlFieldName);
						break;
					default:
						req += String.format(" %1$s LIKE :%2$s ", jpqlFieldName, fieldName);
						fieldValue = "%" + fieldValue + "%";
						break;
				}
				break;
			case "Integer":
			case "Long":
			case "Double":
			case "Decimal":
			case "BigInteger":
			case "Float":
				switch (operator) {
					case OperatorEnum.NOT_EQUAL_1:
					case OperatorEnum.NOT_EQUAL_2:
						req += String.format(" %1$s <> :%2$s ", jpqlFieldName, fieldName);
						break;
					case OperatorEnum.LESS:
						req += String.format(" %1$s < :%2$s ", jpqlFieldName, fieldName);
						break;
					case OperatorEnum.LESS_OR_EQUAL:
						req += String.format(" %1$s <= :%2$s ", jpqlFieldName, fieldName);
						break;
					case OperatorEnum.MORE:
						req += String.format(" %1$s > :%2$s ", jpqlFieldName, fieldName);
						break;
					case OperatorEnum.MORE_OR_EQUAL:
						req += String.format(" %1$s >= :%2$s ", jpqlFieldName, fieldName);
						break;
					case OperatorEnum.BETWEEN:
						params.put(fieldName + START_SUFFIX, start);
						params.put(fieldName + END_SUFFIX, end);
						req += String.format(" %1$s >= :%2$s and %1$s <= :%3$s ", jpqlFieldName, fieldName + START_SUFFIX, fieldName + END_SUFFIX);
						break;
					case OperatorEnum.BETWEEN_OUT:
						params.put(fieldName + START_SUFFIX, start);
						params.put(fieldName + END_SUFFIX, end);
						req += String.format(" %1$s > :%2$s and %1$s < :%3$s ", jpqlFieldName, fieldName + START_SUFFIX, fieldName + END_SUFFIX);
						break;
					case OperatorEnum.BETWEEN_LEFT_OUT:
						params.put(fieldName + START_SUFFIX, start);
						params.put(fieldName + END_SUFFIX, end);
						req += String.format(" %1$s > :%2$s and %1$s <= :%3$s ", jpqlFieldName, fieldName + START_SUFFIX, fieldName + END_SUFFIX);
						break;
					case OperatorEnum.BETWEEN_RIGHT_OUT:
						params.put(fieldName + START_SUFFIX, start);
						params.put(fieldName + END_SUFFIX, end);
						req += String.format(" %1$s >= :%2$s and %1$s < :%3$s ", jpqlFieldName, fieldName + START_SUFFIX, fieldName + END_SUFFIX);
						break;
					case OperatorEnum.IN:
						req += String.format(" %1$s IN :%2$s ", jpqlFieldName, fieldName);
						fieldValue = datas;
						break;
					case OperatorEnum.NOT_IN:
						req += String.format(" %1$s NOT IN :%2$s ", jpqlFieldName, fieldName);
						fieldValue = datas;
						break;
					case OperatorEnum.EXISTS:
						req += String.format(" %1$s IS NOT NULL ", jpqlFieldName);
						break;
					case OperatorEnum.NOT_EXISTS:
						req += String.format(" %1$s IS NULL ", jpqlFieldName);
						break;
					default:
						req += String.format(" %1$s = :%2$s ", jpqlFieldName, fieldName);
						break;
				}
				break;
			case "Date":
				switch (operator) {
					case OperatorEnum.NOT_EQUAL_1:
					case OperatorEnum.NOT_EQUAL_2:
						req += String.format(" DATE(%1$s) <> DATE(:%2$s) ", jpqlFieldName, fieldName);
						break;
					case OperatorEnum.LESS:
						req += String.format(" DATE(%1$s) < DATE(:%2$s) ", jpqlFieldName, fieldName);
						break;
					case OperatorEnum.LESS_OR_EQUAL:
						req += String.format(" DATE(%1$s) <= DATE(:%2$s) ", jpqlFieldName, fieldName);
						break;
					case OperatorEnum.MORE:
						req += String.format(" DATE(%1$s) > DATE(:%2$s) ", jpqlFieldName, fieldName);
						break;
					case OperatorEnum.MORE_OR_EQUAL:
						req += String.format(" DATE(%1$s) >= DATE(:%2$s) ", jpqlFieldName, fieldName);
						break;
					case OperatorEnum.BETWEEN:
						start = (T) new SimpleDateFormat("dd/MM/yyyy").parse(start.toString());
						end = (T) new SimpleDateFormat("dd/MM/yyyy").parse(end.toString());
						params.put(fieldName + START_SUFFIX, start);
						params.put(fieldName + END_SUFFIX, end);
						req += String.format(" DATE(%1$s) >= DATE(:%2$s) and DATE(%1$s) <= DATE(:%3$s) ", jpqlFieldName, fieldName + START_SUFFIX, fieldName + END_SUFFIX);
						break;
					case OperatorEnum.BETWEEN_OUT:
						start = (T) new SimpleDateFormat("dd/MM/yyyy").parse(start.toString());
						end = (T) new SimpleDateFormat("dd/MM/yyyy").parse(end.toString());
						params.put(fieldName + START_SUFFIX, start);
						params.put(fieldName + END_SUFFIX, end);
						req += String.format(" DATE(%1$s) > DATE(:%2$s) and DATE(%1$s) < DATE(:%3$s) ", jpqlFieldName, fieldName + START_SUFFIX, fieldName + END_SUFFIX);
						break;
					case OperatorEnum.BETWEEN_LEFT_OUT:
						start = (T) new SimpleDateFormat("dd/MM/yyyy").parse(start.toString());
						end = (T) new SimpleDateFormat("dd/MM/yyyy").parse(end.toString());
						params.put(fieldName + START_SUFFIX, start);
						params.put(fieldName + END_SUFFIX, end);
						req += String.format(" DATE(%1$s) > DATE(:%2$s) and DATE(%1$s) <= DATE(:%3$s) ", jpqlFieldName, fieldName + START_SUFFIX, fieldName + END_SUFFIX);
						break;
					case OperatorEnum.BETWEEN_RIGHT_OUT:
						start = (T) new SimpleDateFormat("dd/MM/yyyy").parse(start.toString());
						end = (T) new SimpleDateFormat("dd/MM/yyyy").parse(end.toString());
						params.put(fieldName + START_SUFFIX, start);
						params.put(fieldName + END_SUFFIX, end);
						req += String.format(" DATE(%1$s) >= DATE(:%2$s) and DATE(%1$s) < DATE(:%3$s) ", jpqlFieldName, fieldName + START_SUFFIX, fieldName + END_SUFFIX);
						break;
					case OperatorEnum.IN:
						req += String.format(" %1$s IN :%2$s ", jpqlFieldName, fieldName);
						fieldValue = getDatasAsListOfDate(datas);
						break;
					case OperatorEnum.NOT_IN:
						req += String.format(" %1$s NOT IN :%2$s ", jpqlFieldName, fieldName);
						fieldValue = getDatasAsListOfDate(datas);
						break;
					case OperatorEnum.EXISTS:
						req += String.format(" %1$s IS NOT NULL ", jpqlFieldName);
						break;
					case OperatorEnum.NOT_EXISTS:
						req += String.format(" %1$s IS NULL ", jpqlFieldName);
						break;
					default:
						req += String.format(" DATE(%1$s) = DATE(:%2$s) ", jpqlFieldName, fieldName);
						break;
				}
				break;
			case "Time":
				switch (operator) {
					case OperatorEnum.NOT_EQUAL_1:
					case OperatorEnum.NOT_EQUAL_2:
						req += String.format(" TIME(%1$s) <> TIME(:%2$s) ", jpqlFieldName, fieldName);
						break;
					case OperatorEnum.LESS:
						req += String.format(" TIME(%1$s) < TIME(:%2$s) ", jpqlFieldName, fieldName);
						break;
					case OperatorEnum.LESS_OR_EQUAL:
						req += String.format(" TIME(%1$s) <= TIME(:%2$s) ", jpqlFieldName, fieldName);
						break;
					case OperatorEnum.MORE:
						req += String.format(" TIME(%1$s) > TIME(:%2$s) ", jpqlFieldName, fieldName);
						break;
					case OperatorEnum.MORE_OR_EQUAL:
						req += String.format(" TIME(%1$s) >= TIME(:%2$s) ", jpqlFieldName, fieldName);
						break;
					case OperatorEnum.BETWEEN:
						start = (T) new SimpleDateFormat("HH:mm:ss").parse(start.toString());
						end = (T) new SimpleDateFormat("HH:mm:ss").parse(end.toString());
						params.put(fieldName + START_SUFFIX, start);
						params.put(fieldName + END_SUFFIX, end);
						req += String.format(" TIME(%1$s) >= TIME(:%2$s) and TIME(%1$s) <= TIME(:%3$s) ", jpqlFieldName, fieldName + START_SUFFIX, fieldName + END_SUFFIX);
						break;
					case OperatorEnum.BETWEEN_OUT:
						start = (T) new SimpleDateFormat("HH:mm:ss").parse(start.toString());
						end = (T) new SimpleDateFormat("HH:mm:ss").parse(end.toString());
						params.put(fieldName + START_SUFFIX, start);
						params.put(fieldName + END_SUFFIX, end);
						req += String.format(" TIME(%1$s) > TIME(:%2$s) and TIME(%1$s) < TIME(:%3$s) ", jpqlFieldName, fieldName + START_SUFFIX, fieldName + END_SUFFIX);
						break;
					case OperatorEnum.BETWEEN_LEFT_OUT:
						start = (T) new SimpleDateFormat("HH:mm:ss").parse(start.toString());
						end = (T) new SimpleDateFormat("HH:mm:ss").parse(end.toString());
						params.put(fieldName + START_SUFFIX, start);
						params.put(fieldName + END_SUFFIX, end);
						req += String.format(" TIME(%1$s) > TIME(:%2$s) and TIME(%1$s) <= TIME(:%3$s) ", jpqlFieldName, fieldName + START_SUFFIX, fieldName + END_SUFFIX);
						break;
					case OperatorEnum.BETWEEN_RIGHT_OUT:
						start = (T) new SimpleDateFormat("HH:mm:ss").parse(start.toString());
						end = (T) new SimpleDateFormat("HH:mm:ss").parse(end.toString());
						params.put(fieldName + START_SUFFIX, start);
						params.put(fieldName + END_SUFFIX, end);
						req += String.format(" TIME(%1$s) >= TIME(:%2$s) and TIME(%1$s) < TIME(:%3$s) ", jpqlFieldName, fieldName + START_SUFFIX, fieldName + END_SUFFIX);
						break;
					case OperatorEnum.IN:
						req += String.format(" %1$s IN :%2$s ", jpqlFieldName, fieldName);
						fieldValue = getDatasAsListOfTime(datas);
						break;
					case OperatorEnum.NOT_IN:
						req += String.format(" %1$s NOT IN :%2$s ", jpqlFieldName, fieldName);
						fieldValue = getDatasAsListOfTime(datas);
						break;
					case OperatorEnum.EXISTS:
						req += String.format(" %1$s IS NOT NULL ", jpqlFieldName);
						break;
					case OperatorEnum.NOT_EXISTS:
						req += String.format(" %1$s IS NULL ", jpqlFieldName);
						break;
					default:
						req += String.format(" TIME(%1$s) = TIME(:%2$s) ", jpqlFieldName, fieldName);
						break;
				}
				break;
			case "Boolean":
				switch (operator) {
					case OperatorEnum.NOT_EQUAL_1:
					case OperatorEnum.NOT_EQUAL_2:
						req += jpqlFieldName + " <> :" + fieldName;
						break;
					case OperatorEnum.EXISTS:
						req += String.format(" %1$s IS NOT NULL ", jpqlFieldName);
						break;
					case OperatorEnum.NOT_EXISTS:
						req += String.format(" %1$s IS NULL ", jpqlFieldName);
						break;
					default:
						req += jpqlFieldName + " = :" + fieldName;
						break;
				}
				break;
		}
		if (!OperatorEnum.IS_BETWEEN_OPERATOR(operator) && !OperatorEnum.IS_IN_OPERATOR(operator) && "Date".equals(fieldType)) {
			fieldValue = new SimpleDateFormat("dd/MM/yyyy").parse(fieldValue.toString());
		}
		if (!OperatorEnum.IS_BETWEEN_OPERATOR(operator) && (fieldParam == null || OperatorEnum.OPERATOR_NEEDS_FIELD_VALUE(operator))) {
			params.put(fieldName, fieldValue);
		}

		return req;
	}
}