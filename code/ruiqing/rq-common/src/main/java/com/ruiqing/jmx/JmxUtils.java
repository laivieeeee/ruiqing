package com.ruiqing.jmx;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.management.OperatingSystemMXBean;

/**
 * 本地JMX
 * 
 * @author HUANGLIFEI5
 *
 */
public class JmxUtils {

	public static void main(String[] args) throws Exception {
		Map<String, Object> classLoading =  getThread();
		System.out.println(classLoading);
	}
	/**
	 * 虚拟机的类加载信息
	 * 
	 * @return Map
	 * @throws Exception
	 */
	public static Map<String, Object> getClassLoading() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ClassLoadingMXBean cl = ManagementFactory.getClassLoadingMXBean();
		map.put("loadedClassCount", cl.getLoadedClassCount());
		map.put("totalLoadedClassCount", cl.getTotalLoadedClassCount());
		map.put("unloadedClassCount", cl.getUnloadedClassCount());
		map.put("runtimeName", jvmName);
		return map;
	}

	/**
	 * 操作系统信息
	 * 
	 * @return Map
	 * @throws Exception
	 */
	public static Map<String, Object> getSystem() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		OperatingSystemMXBean op = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

		map.put("availableProcessors", op.getAvailableProcessors());
		map.put("committedVirtualMemorySize", op.getCommittedVirtualMemorySize());
		map.put("freePhysicalMemorySize", op.getFreePhysicalMemorySize());
		map.put("freeSwapSpaceSize", op.getFreeSwapSpaceSize());
		map.put("processCpuLoad", op.getProcessCpuLoad());
		map.put("systemCpuLoad", op.getSystemCpuLoad());
		map.put("systemLoadAverage", op.getSystemLoadAverage());
		map.put("totalPhysicalMemorySize", op.getTotalPhysicalMemorySize());
		map.put("totalSwapSpaceSize", op.getTotalSwapSpaceSize());
		map.put("runtimeName", jvmName);
		map.put("jvmTotalMemory", Runtime.getRuntime().totalMemory());
		map.put("jvmFreeMemory", Runtime.getRuntime().freeMemory());
		map.put("jvmMaxMemory", Runtime.getRuntime().maxMemory());
		map.put("jvmAvailableProcessors", Runtime.getRuntime().availableProcessors());
		map.put("physicalMemoryLoad", (op.getTotalPhysicalMemorySize()-op.getFreePhysicalMemorySize())/(double)op.getTotalPhysicalMemorySize());
		map.put("jvmMemoryyLoad", (Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory())/(double)Runtime.getRuntime().totalMemory());

		return map;
	}

	/**
	 * GC信息
	 * 
	 * @return Map
	 * @throws Exception
	 */
	public static List<Map<String, Object>> getGC() throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> m;
		for (GarbageCollectorMXBean d : ManagementFactory.getGarbageCollectorMXBeans()) {
			m = new HashMap<String, Object>();
			m.put("gcName", d.getName());
			m.put("collectionCount", d.getCollectionCount());
			m.put("collectionTime", d.getCollectionTime());
			m.put("runtimeName", jvmName);
			list.add(m);
		}
		return list;
	}

	/**
	 * 虚拟机名称
	 */
	public static String jvmName = null;

	/**
	 * 虚拟机的运行时
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> getJvmInfo() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		RuntimeMXBean mxbean = ManagementFactory.getRuntimeMXBean();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("runtimeName", mxbean.getName());
		map.put("vmVendor", mxbean.getVmVendor());
		map.put("vmName", mxbean.getVmName());
		map.put("vmVersion", mxbean.getVmVersion());
		map.put("managementSpecVersion", mxbean.getManagementSpecVersion());
		map.put("specName", mxbean.getSpecName());
		map.put("specVendor", mxbean.getSpecVendor());
		map.put("specVersion", mxbean.getSpecVersion());
		map.put("startTime", sdf.format(new Date(mxbean.getStartTime())));
		map.put("upTime", mxbean.getUptime());
		map.put("bootClassPath", mxbean.getBootClassPath());
		map.put("classPath", mxbean.getClassPath());
		map.put("inputArguments", mxbean.getInputArguments());
		map.put("libraryPath", mxbean.getLibraryPath());
		map.put("systemProperties", mxbean.getSystemProperties());
		return map;
	}

	/**
	 * 虚拟机的线程
	 * 
	 * @return Map
	 * @throws Exception
	 */
	public static Map<String, Object> getThread() throws Exception {
		ThreadMXBean thread = ManagementFactory.getThreadMXBean();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("peakThreadCount", thread.getPeakThreadCount());
		map.put("threadCount", thread.getThreadCount());
		map.put("daemonThreadCount", thread.getDaemonThreadCount());
		map.put("totalStartedThreadCount", thread.getTotalStartedThreadCount());
		map.put("runtimeName", jvmName);
		return map;
	}

	/**
	 * 虚拟机中的内存池
	 * 
	 * @return List
	 */
	public static List<Map<String, Object>> getMemoryPool() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		MemoryMXBean mem = ManagementFactory.getMemoryMXBean();

		Map<String, Object> heapMemoryUsageMap = new HashMap<String, Object>();
		heapMemoryUsageMap.put("usageCommitted", mem.getHeapMemoryUsage().getCommitted());
		heapMemoryUsageMap.put("usageInit", mem.getHeapMemoryUsage().getInit());
		heapMemoryUsageMap.put("usageMax", mem.getHeapMemoryUsage().getMax());
		heapMemoryUsageMap.put("usageUsed", mem.getHeapMemoryUsage().getUsed());
		heapMemoryUsageMap.put("memoryPoolType", "Heap memory");
		heapMemoryUsageMap.put("memoryPoolName", "Heap memory");
		heapMemoryUsageMap.put("runtimeName", jvmName);
		list.add(heapMemoryUsageMap);

		Map<String, Object> nonHeapMemoryUsageMap = new HashMap<String, Object>();
		nonHeapMemoryUsageMap.put("usageCommitted", mem.getNonHeapMemoryUsage().getCommitted());
		nonHeapMemoryUsageMap.put("usageInit", mem.getNonHeapMemoryUsage().getInit());
		nonHeapMemoryUsageMap.put("usageMax", mem.getNonHeapMemoryUsage().getMax());
		nonHeapMemoryUsageMap.put("usageUsed", mem.getNonHeapMemoryUsage().getUsed());
		nonHeapMemoryUsageMap.put("memoryPoolType", "Non-heap memory");
		nonHeapMemoryUsageMap.put("memoryPoolName", "Non-heap memory");
		nonHeapMemoryUsageMap.put("runtimeName", jvmName);
		list.add(nonHeapMemoryUsageMap);

		List<MemoryPoolMXBean> mps = ManagementFactory.getMemoryPoolMXBeans();
		mps.forEach(mp -> {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("memoryPoolName", mp.getName());
			map.put("memoryPoolType", mp.getType().toString());
			map.put("runtimeName", jvmName);
			if (null != mp.getUsage()) {
				map.put("usageInit", mp.getUsage().getInit());
				map.put("usageUsed", mp.getUsage().getUsed());
				map.put("usageCommitted", mp.getUsage().getCommitted());
				map.put("usageMax", mp.getUsage().getMax());
			}
			list.add(map);
		});
		return list;
	}
}
