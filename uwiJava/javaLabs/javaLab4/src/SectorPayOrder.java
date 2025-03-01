package Java.uwiJava.javaLabs.javaLab4.src;

import java.util.Comparator;

class SectorPayOrder implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        // sort by sector
        int sectorCompare = p1.getSector().compareTo(p2.getSector());

        // if sectors equal, sort by pay ratwe
        if (sectorCompare == 0) {
            return Double.compare(p1.getPay(), p2.getPay());
        }

        return sectorCompare;
    }
}
