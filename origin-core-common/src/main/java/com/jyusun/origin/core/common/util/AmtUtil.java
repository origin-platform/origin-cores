//package com.jyusun.origin.core.common.util;
//
//import lombok.experimental.UtilityClass;
//import org.apache.commons.lang3.StringUtils;
//
//import java.math.BigDecimal;
//
///**
// * @author jyusun at
// */
//@UtilityClass
//public class AmtUtil {
//
////    public static void main(String[] args) {
////        long l = System.currentTimeMillis();
////        System.out.println(AmtUtil.moneyToChinese(new BigDecimal("99999999999999999.01")));
////        System.out.println(System.currentTimeMillis() - l);
////    }
////
////    public static String moneyToChinese(BigDecimal i_money) {
////        if (i_money.equals(BigDecimal.ZERO)) {
////            return "零圆整";
////        }
////        Double max = 1000000000000D;
////        Double min = 0.01D;
////        if (i_money.doubleValue() >= max || i_money.doubleValue() < min) {
////            return "大于1万亿或小于1分了";
////        }
////        i_money = i_money.setScale(2, RoundingMode.HALF_UP);
////        String numStr = i_money.toString();
////        int pointPos = numStr.indexOf('.');
////        //整数部分
////        String s_int = null;
////        //小数部分
////        String s_point = null;
////        if (pointPos >= 0) {
////            s_int = numStr.substring(0, pointPos);
////            s_point = numStr.substring(pointPos + 1);
////        } else {
////            s_int = numStr;
////        }
////        StringBuilder sb = new StringBuilder();
////        if (!"0".equals(s_int)) {
////            int groupCount = (int) Math.ceil(s_int.length() / 4.0);
////            for (int group = 0; group < groupCount; group++) {
////                boolean zeroFlag = true;
////                boolean noZeroFlag = false;
////                int start = (s_int.length() % 4 == 0 ? 0 : (s_int.length() % 4 - 4)) + 4 * group;
////                for (int i = 0; i < 4; i++) {
////                    if (i + start >= 0) {
////                        int value = s_int.charAt(i + start) - '0';
////                        if (value > 0) {
////                            sb.append(CN_UPPER_NUMBER[value]);
////                            if (i < 3) {
////                                sb.append(CN_UPPER_UNIT[i]);
////                            }
////                            zeroFlag = true;
////                            noZeroFlag = true;
////                        } else if (zeroFlag) {
////                            sb.append('零');
////                            zeroFlag = false;
////                        }
////                    }
////                }
////                if (sb.charAt(sb.length() - 1) == '零') {
////                    sb.deleteCharAt(sb.length() - 1);
////                }
////                if (noZeroFlag || groupCount - group == 1) {
////                    sb.append(CN_GROUP[groupCount - group - 1]);
////                }
////            }
////        }
////        if (s_point == null || "00".equals(s_point)) {
////            sb.append('整');
////        } else {
////            int j = s_point.charAt(0) - '0';
////            int f = s_point.charAt(1) - '0';
////            if (j > 0) {
////                sb.append(CN_UPPER_NUMBER[j]).append('角');
////                if (f != 0) {
////                    sb.append(CN_UPPER_NUMBER[f]).append('分');
////                }
////            } else if ("0".equals(s_int)) {
////                sb.append(CN_UPPER_NUMBER[f]).append('分');
////            } else {
////                sb.append('零').append(CN_UPPER_NUMBER[f]).append('分');
////            }
////        }
////        return sb.toString();
////    }
////
////
////    private static final char[] CN_UPPER_NUMBER = "零壹贰叁肆伍陆柒捌玖".toCharArray();
////    private static final char[] CN_UPPER_UNIT = "仟佰拾".toCharArray();
////    private static final char[] CN_GROUP = "圆万亿".toCharArray();
//
//
//    /**
//     * 大写数字
//     */
//    private static final char[] CN_UPPER_NUMBERS = {'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'};
//    /**
//     * 整数部分的单位
//     */
//    private static final char[] CN_UPPER_UNIT = {'元', '拾', '佰', '仟', '万', '拾', '佰', '仟', '亿', '拾', '佰', '仟', '万', '拾', '佰', '仟'};
//    /**
//     * 小数部分的单位
//     */
//    private static final char[] CN_UPPER_DUNIT = {'角', '分', '厘'};
//    /**
//     * 小数部分的单位
//     */
//    private static final char[] CN_UPPER = {'整', '负'};
//
//
//    /**
//     * 转换
//     *
//     * @param amt
//     * @return
//     */
//    public String toChinese(BigDecimal amt) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(minusNumber(amt))
//                .append(zeroEnd(amt))
//                .append(amtEnd(amt));
//        return sb.toString();
//    }
//
//    /**
//     * 起始位置 小于0 起始为负
//     *
//     * @param amt 金额
//     * @return
//     */
//    public static String minusNumber(BigDecimal amt) {
//        return BigDecimal.ZERO.compareTo(amt) < 0 ? "负" : StringUtil.EMPTY;
//    }
//
//    /**
//     * 截止位置 零元
//     *
//     * @param amt 金额
//     * @return
//     */
//    public static String zeroEnd(BigDecimal amt) {
//        return BigDecimal.ZERO.compareTo(amt) == 0 ? "零元" : StringUtil.EMPTY;
//    }
//
//    /**
//     * 截止位置  起始为负
//     *
//     * @param amt 金额
//     * @return
//     */
//    public static String amtEnd(BigDecimal amt) {
//        return BigDecimal.ZERO.compareTo(amt.remainder(BigDecimal.ONE)) == 0 ? "元整" : StringUtil.EMPTY;
//    }
//
//
//    /**
//     * 转换为大写的中文金额
//     *
//     * @param str 字符串类型的 金额数字
//     * @return
//     */
//    public static String toChinese(String str) {
//// 判断输入的金额字符串是否符合要求
//        if (StringUtils.isBlank(str) || !str.matches("(-)?[\\d]*(.)?[\\d]*")) {
//            return "抱歉，请输入数字！";
//        }
//
//        if ("0".equals(str) || "0.00".equals(str) || "0.0".equals(str)) {
//            return "零元";
//        }
//
//// 判断金额数字中是否存在负号"-"
//        boolean flag = false;
//        if (str.startsWith("-")) {
//// 标志位，标志此金额数字为负数
//            flag = true;
//            str = str.replaceAll("-", "");
//        }
//
//// 去掉金额数字中的逗号","
//        str = str.replaceAll(",", "");
//        String integerStr;//整数部分数字
//        String decimalStr;//小数部分数字
//
//// 初始化：分离整数部分和小数部分
//        if (str.indexOf(".") > 0) {
//            integerStr = str.substring(0, str.indexOf("."));
//            decimalStr = str.substring(str.indexOf(".") + 1);
//        } else if (str.indexOf(".") == 0) {
//            integerStr = "";
//            decimalStr = str.substring(1);
//        } else {
//            integerStr = str;
//            decimalStr = "";
//        }
//
//// beyond超出计算能力，直接返回
//        if (integerStr.length() > IUNIT.length) {
//            return "超出计算能力！";
//        }
//
//// 整数部分数字
//        int[] integers = toIntArray(integerStr);
//// 判断整数部分是否存在输入012的情况
//        if (integers.length > 1 && integers[0] == 0) {
//            return "抱歉，输入数字不符合要求！";
//        }
//// 设置万单位
//        boolean isWan = isWan5(integerStr);
//// 小数部分数字
//        int[] decimals = toIntArray(decimalStr);
//// 返回最终的大写金额
//        String result = getChineseInteger(integers, isWan) + getChineseDecimal(decimals);
//        if (flag) {
//// 如果是负数，加上"负"
//            return "负" + result;
//        } else {
//            return result;
//        }
//    }
//
//    /**
//     * 将字符串转为int数组
//     *
//     * @param number 数字
//     * @return
//     */
//    private static int[] toIntArray(String number) {
//        int[] array = new int[number.length()];
//        for (int i = 0; i < number.length(); i++) {
//            array[i] = Integer.parseInt(number.substring(i, i + 1));
//        }
//        return array;
//    }
//
//    /**
//     * 将整数部分转为大写的金额
//     *
//     * @param integers 整数部分数字
//     * @param isWan    整数部分是否已经是达到【万】
//     * @return
//     */
//    public static String getChineseInteger(int[] integers, boolean isWan) {
//        StringBuffer chineseInteger = new StringBuffer("");
//        int length = integers.length;
//        if (length == 1 && integers[0] == 0) {
//            return "";
//        }
//        for (int i = 0; i < length; i++) {
//            String key = "";
//            if (integers[i] == 0) {
//                if ((length - i) == 13)//万（亿）
//                    key = IUNIT[4];
//                else if ((length - i) == 9) {//亿
//                    key = IUNIT[8];
//                } else if ((length - i) == 5 && isWan) {//万
//                    key = IUNIT[4];
//                } else if ((length - i) == 1) {//元
//                    key = IUNIT[0];
//                }
//                if ((length - i) > 1 && integers[i + 1] != 0) {
//                    key += NUMBERS[0];
//                }
//            }
//            chineseInteger.append(integers[i] == 0 ? key : (NUMBERS[integers[i]] + IUNIT[length - i - 1]));
//        }
//        return chineseInteger.toString();
//    }
//
//    /**
//     * 将小数部分转为大写的金额
//     *
//     * @param decimals 小数部分的数字
//     * @return
//     */
//    private static String getChineseDecimal(int[] decimals) {
//        StringBuffer chineseDecimal = new StringBuffer("");
//        for (int i = 0; i < decimals.length; i++) {
//            if (i == 3) {
//                break;
//            }
//            chineseDecimal.append(decimals[i] == 0 ? "" : (NUMBERS[decimals[i]] + DUNIT[i]));
//        }
//        return chineseDecimal.toString();
//    }
//
//    /**
//     * 判断当前整数部分是否已经是达到【万】
//     *
//     * @param integerStr 整数部分数字
//     * @return
//     */
//    private static boolean isWan5(String integerStr) {
//        int length = integerStr.length();
//        if (length > 4) {
//            String subInteger = "";
//            if (length > 8) {
//                subInteger = integerStr.substring(length - 8, length - 4);
//            } else {
//                subInteger = integerStr.substring(0, length - 4);
//            }
//            return Integer.parseInt(subInteger) > 0;
//        } else {
//            return false;
//        }
//    }
//
//
//    // Test
//    public static void main(String[] args) {
//        String number = "12.56";
//        System.out.println(number + ": " + AmtUtil.toChinese(number));
//
//        number = "1234567890563886.123";
//        System.out.println(number + ": " + AmtUtil.toChinese(number));
//
//        number = "1600";
//        System.out.println(number + ": " + AmtUtil.toChinese(number));
//
//        number = "156,0";
//        System.out.println(number + ": " + AmtUtil.toChinese(number));
//
//        number = "-156,0";
//        System.out.println(number + ": " + AmtUtil.toChinese(number));
//
//        number = "0.12";
//        System.out.println(number + ": " + AmtUtil.toChinese(number));
//
//        number = "0.0";
//        System.out.println(number + ": " + AmtUtil.toChinese(number));
//
//        number = "01.12";
//        System.out.println(number + ": " + AmtUtil.toChinese(number));
//
//        number = "0125";
//        System.out.println(number + ": " + AmtUtil.toChinese(number));
//
//        number = "-0125";
//        System.out.println(number + ": " + AmtUtil.toChinese(number));
//
//        number = "sdw5655";
//        System.out.println(number + ": " + AmtUtil.toChinese(number));
//
//        System.out.println(null + ": " + AmtUtil.toChinese(null));
//    }
//
//}
