package pe.gob.mef.sisevent.bs.mem;

public class MemoryWarningListenerImpl implements Listener {

    public void memoryUsageLow(long usedMemory, long maxMemory) {
        System.out.println("Memory usage low!!!");
        double percentageUsed = (double) usedMemory / maxMemory;
        System.out.println("percentageUsed = " + percentageUsed);
        MemoryThreadDumper threadDumper = new MemoryThreadDumper();        
        System.out.println("DUMP: \n"+threadDumper.dumpStacksS());
    }

}