package pe.gob.mef.sisevent.bs.mem;

public interface Listener {
	public void memoryUsageLow(long usedMemory, long maxMemory);
}
