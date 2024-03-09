import java.util.*;

class NoteBook{
    private int ram;
    private int hddSpace;
    private String manufacturer;
    private  String os;
    private String color;
    //Создание экземпляра
    public NoteBook(int ram, int hddSpace, String manufacturer, String os, String color){
        this.ram = ram;
        this.hddSpace = hddSpace;
        this.manufacturer = manufacturer;
        this.os = os;
        this.color = color;
    }
    public int getRam(){
        return ram;
    }
    public int getHddSpace(){
        return hddSpace;
    }
    public String getManufacturer(){
        return manufacturer;
    }
    public String getOs(){
        return os;
    }
    public String getColor(){
        return color;
    }
    public void filter(){

    }
    //Переопределение toString для вывода экземпляров класса
    @Override
    public String toString() {
        return "Desktop{" +
                "ОЗУ='" + ram + '\'' +
                ", Ёмкость диска='" + hddSpace + '\'' +
                ", Производитель='" + manufacturer + '\'' +
                ", ОС='" + os + '\'' +
                ", Цвет=" + color +
                '}';
    }
    //Переопределение hash и equals, чтобы избежать повторений в множестве
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        NoteBook NoteBook = (NoteBook) obj;
        return NoteBook.ram == ram &&
                NoteBook.hddSpace == hddSpace &&
                NoteBook.manufacturer.equals(manufacturer) &&
                NoteBook.os.equals(os)&&
                NoteBook.color.equals(color);


    }
    @Override
    public int hashCode() {
        return Objects.hash(ram,hddSpace,manufacturer,os,color);
    }
}

public class Desktop {
    static Scanner scanner = new Scanner(System.in);
    //Для хранения параметров содержащих строку
    public static Map<String,String> mapString;
    //Для хранения параметров содержащих числа
    public static Map<String,Integer> mapInt;
    //Множество для Ноутбуков
    public static Set<NoteBook> setDesktop;

    public static void main(String[] args) {

        mapString = new HashMap<>();

        mapInt = new HashMap<>();


        setDesktop = new HashSet<>();
        // Экземпляры классов
        NoteBook desktop = new NoteBook(16,1024,"Maibenben","Windows","Black");
        NoteBook desktop2 = new NoteBook(8,512,"Acer","Windows","Black");
        NoteBook desktop3 = new NoteBook(16,1024,"Lenovo","Windows","Gray");
        NoteBook desktop4 = new NoteBook(8,256,"ASUS","Linux","Black");
        NoteBook desktop5 = new NoteBook(4,128,"Acer","Windows","White");
        NoteBook desktop6 = new NoteBook(4,128,"Acer","Windows","White");
        NoteBook desktop7 = new NoteBook(8,512,"Lenovo","Linux","Black");
        NoteBook desktop8 = new NoteBook(16,1024,"Lenovo","Windows","Gray");
        //Добавление в множество
        setDesktop.add(desktop);
        setDesktop.add(desktop2);
        setDesktop.add(desktop3);
        setDesktop.add(desktop4);
        setDesktop.add(desktop5);
        setDesktop.add(desktop6);
        setDesktop.add(desktop7);
        setDesktop.add(desktop8);

        filter();
    }
    //Фильтрует по критериям, которые вводит пользователь
    public static void filter(){

        // Взаимодействие с пользователем
        while (true) {
            System.out.println("\n Введите характеристику: \n 1. ОЗУ \n 2. Ёмкость жесткого диска" +
                    " \n 3. Производитель \n 4. Операционная система. \n 5. Цвет корпуса" +
                    "\n 6. Применить фильтр \n 7. Вывести список ноутбуков");

            String input = scanner.nextLine();
            if (input.equals("1")) {
                System.out.println("Введите ОЗУ");
                int ram = Integer.parseInt(scanner.nextLine());
                mapInt.put("ram", ram);

            } else if (input.equals("2")) {
                System.out.println("Введите ёмкость жесткого диска");
                int hdd = Integer.parseInt(scanner.nextLine());
                mapInt.put("hddSpace", hdd);

            } else if (input.equals("3")) {
                System.out.println("Введите производителя");
                String manufacturer = scanner.nextLine();
                mapString.put("manufacturer", manufacturer);

            }else if (input.equals("4")) {
                System.out.println("Введите операционнную систему");
                String os = scanner.nextLine();
                mapString.put("os", os);

            } else if (input.equals("5")) {
                System.out.println("Введите цвет корпуса");
                String color = scanner.nextLine();
                mapString.put("color", color);

            }
            else if (input.equals("6")) {
                System.out.println("Результат запроса:");
                break;

            } else if (input.equals("7")) {
                System.out.println("Ваш запрос");
                for(NoteBook c:setDesktop){
                    System.out.println(c);
                }
            } else {
                System.out.println("Такой характеристики нет");
            }
        }
        //Фильтрация по параметрам
        Set<NoteBook> copyDesktopSet = new HashSet<>();
        copyDesktopSet.addAll(setDesktop);

        Iterator<NoteBook> iterator = copyDesktopSet.iterator();
        while (iterator.hasNext()) {
            NoteBook i = iterator.next();
            if (mapInt.get("ram") != null && i.getRam() != mapInt.get("ram")) {
                iterator.remove();
                continue;
            }
            if (mapInt.get("hddSpace") != null && i.getHddSpace() != mapInt.get("hddSpace")) {
                iterator.remove();
                continue;
            }
            if (mapString.get("manufacturer") != null && !i.getManufacturer().equals(mapString.get("manufacturer"))) {
                iterator.remove();
                continue;
            }
            if (mapString.get("os") != null && !i.getOs().equals(mapString.get("os"))) {
                iterator.remove();
                continue;

            }if (mapString.get("color") != null && !i.getColor().equals(mapString.get("color"))) {
                iterator.remove();
                continue;
            }
        }
        //Вывод запроса
        for (NoteBook i : copyDesktopSet) {
            System.out.println(i);
        }
    }
}
