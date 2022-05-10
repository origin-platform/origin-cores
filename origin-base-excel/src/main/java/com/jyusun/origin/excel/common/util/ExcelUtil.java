package com.jyusun.origin.excel.common.util;

import com.alibaba.excel.EasyExcel;
import com.jyusun.origin.core.common.util.WebUtil;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel处理
 *
 * @author jyusun at 2022-1-26 14:07:40
 */
@UtilityClass
public class ExcelUtil {

    @SneakyThrows
    public static boolean simpleExport(String fileName, String sheetName, List<?> datas,
                                       Class<?> clazz) {
        HttpServletResponse response = WebUtil.getResponse();
        WebUtil.setFileNameHeader(response, fileName);
        EasyExcel.write(response.getOutputStream(), clazz)
                .useDefaultStyle(false).sheet(sheetName).doWrite(datas);
        return true;
    }

}