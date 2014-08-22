package ua.np.services.smsinfo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;
/*
 *	Formsts the MsSql DF yyyy-MM-ddTHH:mm:sss+hh:mm to oracle DF yyyy-MM-dd HH:mm:ss
 *
 *	Not used yet
 *
 */
public class MssqlToOracleDateAdapter extends XmlAdapter<String, Date>{
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public String marshal(Date v) throws Exception {
        return dateFormat.format(v);
    }

    @Override
    public Date unmarshal(String v) throws Exception {
        return dateFormat.parse(v);
    }
}
