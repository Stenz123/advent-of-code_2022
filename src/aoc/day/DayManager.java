package aoc.day;



import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class DayManager {
    private Day[] days;

    public DayManager(){
        Class[] classes = null;
        try{
            classes = getClasses("aoc.day.days");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        for (Class c: classes) {
            try {
                String cName = c.getSimpleName();
                if(cName.startsWith("Day")&&Day.class.isAssignableFrom(c)){
                    Day day = (Day) c.getConstructor().newInstance();
                    System.out.println(day.solve());
                }else {
                    throw new RuntimeException("Class "+cName+" doesnt extend day or doesnt start with 'Day'");
                }
            } catch (Exception e){
            throw new RuntimeException(e);
            }
        }

    }


    private static Class[] getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration resources = classLoader.getResources(path);
        List dirs = new ArrayList();
        while (resources.hasMoreElements()) {
            URL resource = (URL) resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList classes = new ArrayList();
        for (Object directory : dirs) {
            classes.addAll(findClasses((File) directory, packageName));
        }
        return (Class[]) classes.toArray(new Class[classes.size()]);
    }

    private static List findClasses(File directory, String packageName) throws ClassNotFoundException {
        List classes = new ArrayList();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
