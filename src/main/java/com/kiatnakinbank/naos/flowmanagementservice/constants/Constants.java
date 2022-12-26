package com.kiatnakinbank.naos.flowmanagementservice.constants;

public class Constants {
    public static final class HttpMethod {
        public static final String GET = "GET";
        public static final String PUT = "PUT";
        public static final String POST = "POST";
    }

    public static final class ResponseCode {
        public static final int OK = 200;
        public static final int CREATED = 201; // ข้อมูลถูกเพิ่มเรียบร้อยแล้ว
        public static final int ACCEPTED = 202; // เซิฟเวอร์ได้รับ request แล้ว แต่ยังทำงานไม่เสร็จ
        public static final int NO_CONTENT = 204; // เซิฟเวอร์ได้ทำงานที่ต้องการเสร็จแล้ว แต่ไม่ได้มีการตอบ response body กลับไป
        public static final int NOT_MODIFIED = 304; // สิ่งที่ request ร้องขอไม่ได้มีการเปลี่ยนแปลง ทำให้ไม่มีการร้องขอข้อมูลใหม่จากเซิฟเวอร์
        public static final int BAD_REQUEST = 400;//เซิฟเวอร์ไม่เข้าใจสิ่งที่ client ร้องขอมา ซึ่งอาจเกิดจากการที่ request ผิดรูปแบบที่ต้องการ
        public static final int UNAUTHORIZED = 401;//เกิดจากการที่ client ไม่ได้ทำ authenticate มาก่อน ทำให้เซิฟเวอร์ไม่สามารถให้ request นี้ทำงานได้
        public static final int FORBIDDEN = 403;//คล้ายกับ 401 แต่กรณีนี้เซิฟเวอร์รู้ว่า client เป็นใคร แต่ client ไม่มีสิทธ์ในการเข้าถึงข้อมูลที่ต้องการ
        public static final int PAYLOAD_TOO_LARGE = 413;// เกิดจาก request ที่ client ส่งมามีขนาดใหญ่เกินกว่าที่เซิฟเวอร์รองรับ
        public static final int UNPROCESSABLE_ENTITY = 422;//เกิดจาก request ที่ส่งเข้ามามีรูปแบบที่ถูกต้อง แต่ข้อมูลที่ส่งเข้ามาไม่ถูกต้อง หรือไม่ครบตามความต้องการของเซิฟเวอร์
        public static final int INTERNAL_SERVER_ERROR = 500; //เกิดการทำงานที่ไม่สมบูรณ์ขึ้นที่เซิฟเวอร์ ทำให้ไม่สามารถตอบกลับ client ตามที่ร้องขอมาได้
        public static final int SERVICE_UNAVAILABLE = 503; //ขณะนี้เซิฟเวอร์ไม่สามารถทำงานได้ชั่วคราว อาจเกิดจากการทำงานหนักเกินไป หรือกำลังอยู่ในช่วงแก้ไขระบบสามารถดูข้อมูล HTTP response code ทั้งหมดได้ List of HTTP status codes
        public static final int CONFLICT = 409;
    }

    public static final class ResponseDescription {
        public static final String OK = "Success";
        public static final String CREATED = "Created"; // ข้อมูลถูกเพิ่มเรียบร้อยแล้ว
        public static final String ACCEPTED = "Accepted"; // เซิฟเวอร์ได้รับ request แล้ว แต่ยังทำงานไม่เสร็จ
        public static final String NO_CONTENT = "No Content"; // เซิฟเวอร์ได้ทำงานที่ต้องการเสร็จแล้ว แต่ไม่ได้มีการตอบ response body กลับไป
        public static final String NOT_MODIFIED = "Not Modified"; // สิ่งที่ request ร้องขอไม่ได้มีการเปลี่ยนแปลง ทำให้ไม่มีการร้องขอข้อมูลใหม่จากเซิฟเวอร์
        public static final String BAD_REQUEST = "Bad Request";//เซิฟเวอร์ไม่เข้าใจสิ่งที่ client ร้องขอมา ซึ่งอาจเกิดจากการที่ request ผิดรูปแบบที่ต้องการ
        public static final String UNAUTHORIZED = "Unauthorized";//เกิดจากการที่ client ไม่ได้ทำ authenticate มาก่อน ทำให้เซิฟเวอร์ไม่สามารถให้ request นี้ทำงานได้
        public static final String FORBIDDEN = "Forbidden";//คล้ายกับ 401 แต่กรณีนี้เซิฟเวอร์รู้ว่า client เป็นใคร แต่ client ไม่มีสิทธ์ในการเข้าถึงข้อมูลที่ต้องการ
        public static final String PAYLOAD_TOO_LARGE = "Payload Too Large";// เกิดจาก request ที่ client ส่งมามีขนาดใหญ่เกินกว่าที่เซิฟเวอร์รองรับ
        public static final String UNPROCESSABLE_ENTITY = "Unprocessable Entity";//เกิดจาก request ที่ส่งเข้ามามีรูปแบบที่ถูกต้อง แต่ข้อมูลที่ส่งเข้ามาไม่ถูกต้อง หรือไม่ครบตามความต้องการของเซิฟเวอร์
        public static final String INTERNAL_SERVER_ERROR = "Internal Server Error"; //เกิดการทำงานที่ไม่สมบูรณ์ขึ้นที่เซิฟเวอร์ ทำให้ไม่สามารถตอบกลับ client ตามที่ร้องขอมาได้
        public static final String SERVICE_UNAVAILABLE = "Service Unavailable"; //ขณะนี้เซิฟเวอร์ไม่สามารถทำงานได้ชั่วคราว อาจเกิดจากการทำงานหนักเกินไป หรือกำลังอยู่ในช่วงแก้ไขระบบสามารถดูข้อมูล HTTP response code ทั้งหมดได้ List of HTTP status codes
        public static final String CONFLICT = "Conflict or duplicate record";
    }

    public static final class FormatSystem {
        public static final String FORMAT_DATETIMEZONE = "yyyy-MM-dd HH:mm:ss.SSSZ";
        // -------------------------------------------- Years ahead -------------------------------------------------------
        // YYYY-MM-DD HH:MM:SS
        public static final String DATETIMEFULL_YMD_PATTERN = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";
        public static final String DATETIMEFULL_YMD = "yyyy-MM-dd HH:mm:ss";

        // YYYY-MM-DD HH:MM
        public static final String DATETIME_YMD_PATTERN = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}";
        public static final String DATETIME_YMD = "yyyy-MM-dd HH:mm";

        // YYYY-MM-DD
        public static final String DATE_YMD_PATTERN = "\\d{4}-\\d{2}-\\d{2}";
        public static final String DATE_YMD = "yyyy-MM-dd";

        // YYYY/MM/DD
        public static final String DATESLASH_YMD_PATTERN = "\\d{4}/\\d{2}/\\d{2}";
        public static final String DATESLASH_YMD = "yyyy/MM/dd";

        // YYYY.MM.DD
        public static final String DATEDOT_YMD_PATTERN = "\\d{4}.\\d{2}.\\d{2}";
        public static final String DATEDOT_YMD = "yyyy.MM.dd";

        // -------------------------------------------- The day ahead -------------------------------------------------------
        // DD-MM-YYYY HH:MM:SS
        public static final String DATETIMEFULL_DMY_PATTERN = "\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}:\\d{2}";
        public static final String DATETIMEFULL_DMY = "dd-MM-yyyy HH:mm:ss";

        // DD-MM-YYYY HH:MM
        public static final String DATETIME_DMY_PATTERN = "\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}";
        public static final String DATETIME_DMY = "dd-MM-yyyy HH:mm";

        // DD-MM-YYYY
        public static final String DATE_DMY_PATTERN = "\\d{2}-\\d{2}-\\d{4}";
        public static final String DATE_DMY = "dd-MM-yyyy";

        // DD/MM/YYYY HH:MM:SS
        public static final String DATETIMESLASHFULL_DMY_PATTERN = "\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}";
        public static final String DATETIMESLASHFULL_DMY = "dd/MM/yyyy HH:mm:ss";

        // DD/MM/YYYY HH:MM
        public static final String DATETIMESLASH_DMY_PATTERN = "\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}";
        public static final String DATETIMESLASH_DMY = "dd/MM/yyyy HH:mm";

        // DD/MM/YYYY
        public static final String DATESLASH_DMY_PATTERN = "\\d{2}/\\d{2}/\\d{4}";
        public static final String DATESLASH_DMY = "dd/MM/yyyy";

        // DD.MM.YYYY
        public static final String DATEDOT_DMY_PATTERN = "\\d{2}.\\d{2}.\\d{4}";
        public static final String DATEDOT_DMY = "dd.MM.yyyy";
        // ---------------------------------------------------------------------------------------------------

        public static final String DATEISNULL_PATTERN = "\\d{2}:\\d{2}";
        public static final String TIMEZONE_ASIA = "Asia/Bangkok";
        public static final String TIMESTART = "00:00";
        public static final String TIMEEND = "23:59";
    }
}
