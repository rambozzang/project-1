package com.tigerbk.project1.common.vo;

import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * @author : tigerBK
 * @version : 1.0.0
 * @Package : com.withuslaw.common
 * @name : AuthCont.java
 * @date : 2023/09/14 8:41 AM
 **/
@Component
public class CommCont {

    public static final String BF_MATCH_BASE_BIZ_NO = "8888888888";
    public static final String CNTR_RGSTR_GB_CD_BF = "01";
    public static final String CNTR_RGSTR_GB_CD_ES = "02";
    public static final String RESULT_CODE_SUCCESS = "00";
    public static final String RESULT_CODE_FAIL = "99";

    /////////////////////////////////////////////////////////////////////////////////////////////////Bankle CommConsts
    public static final String M0109PARAMETER_PAGE_SIZE = "M0109pageSize";
    public static final int M0109PAGE_SIZE_VAL = 10;
    public static final String M0109PARAMETER_START_ROW = "M0109startRow";

    public static final String MANAGER_PARAMETER_START_ROW = "managerStartRow";

    public static final String USER_PARAMETER_PAGE_SIZE = "userPageSize";
    public static final int USER_PAGE_SIZE_VAL = 10;
    public static final int PAGE_SIZE_VAL = 15;

    public static final String WEB_ROOT = "From Properties";
    public static final String ASSET_ROOT = "From Properties";

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 날짜 포맷 형식
    /**
     * 형식 : 20230101123456
     */
    public static final String PATTERN_yyyyMMddhhmmss = "yyyyMMddhhmmss";
    /**
     * 형식 : 2023-01-01 12:00:00.000
     */
    public static final String PATTERN_yyyyMMdd_HH_mm_ss_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    /**
     * 형식 : 2021-09-02
     */
    public static final String PATTERN_yyyy_MM_dd = "yyyy-MM-dd";
    /**
     * 형식 : 20230101
     */
    public static final String PATTERN_yyyyMMdd = "yyyyMMdd";
    /**
     * 형식 : 14:56:20.669
     */
    public static final String PATTERN_HH_mm_ss_SSS = "HH:mm:ss.SSS";
    /**
     * 형식 : 2021년 9월 2일 목요일
     */
    public static final DateTimeFormatter PATTERN_DATE_FULL = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
    /**
     * 형식 : 2021년 9월 2일 (목)
     */
    public static final DateTimeFormatter PATTERN_DATE_LONG = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
    /**
     * 형식 : 2021. 9. 2
     */
    public static final DateTimeFormatter PATTERN_DATE_MEDIUM = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
    /**
     * 형식 : 21. 9. 2
     */
    public static final DateTimeFormatter PATTERN_DATE_SHORT= DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
    /**
     * 형식 : 오후 2시 56분 20초
     */
    public static final DateTimeFormatter PATTERN_TIME_LONG = DateTimeFormatter.ofLocalizedTime(FormatStyle.LONG);
    /**
     * 형식 : 오후 2:56:20
     */
    public static final DateTimeFormatter PATTERN_TIME_MEDIUM = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
    /**
     * 형식 : 오후 2:56
     */
    public static final DateTimeFormatter PATTERN_TIME_SHORT = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
    /**
     * 형식 : 2021년 9월 2일 (목) 오후 2시 56분 20초
     */
    public static final DateTimeFormatter PATTERN_DATE_TIME_LONG =DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
    /**
     * 형식 : 2021. 9. 2 오후 2:56:20
     */
    public static final DateTimeFormatter PATTERN_DATE_TIME_MEDIUM =DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
    /**
     * 형식 : 21. 9. 2 오후 2:56
     */
    public static final DateTimeFormatter PATTERN_DATE_TIME_SHORT =DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}