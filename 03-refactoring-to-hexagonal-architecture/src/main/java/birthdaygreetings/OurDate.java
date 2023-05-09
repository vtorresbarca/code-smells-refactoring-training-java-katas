package birthdaygreetings;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class OurDate {

    private Date date;

    public OurDate(String yyyyMMdd) throws ParseException {
        //esto debería estar fuera de la clase de dominio, habria que llevar este parse al repositorio
        //porque a esta clase le debería llegar la fecha ya parseada. Esta responsabilidad debería ser del FileEmployeeRepository
        //porque es el que se encarga del formato de fecha. Además que la ParseException es genérica y de infra
        date = new SimpleDateFormat("yyyy/MM/dd").parse(yyyyMMdd);
    }

    public int getDay() {
        return getPartOfDate(GregorianCalendar.DAY_OF_MONTH);
    }

    public int getMonth() {
        return 1 + getPartOfDate(GregorianCalendar.MONTH);
    }

    public boolean isSameDay(OurDate anotherDate) {
        return anotherDate.getDay() == this.getDay()
                && anotherDate.getMonth() == this.getMonth();
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof OurDate))
            return false;
        OurDate other = (OurDate) obj;
        return other.date.equals(this.date);
    }

    private int getPartOfDate(int part) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(part);
    }
}
