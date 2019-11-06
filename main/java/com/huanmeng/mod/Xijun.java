package com.huanmeng.mod;
/*
 * ��ֹ������Դ��
 * ����QQ1871735932
 * ��������jd-gui֮�������鿴�������ܸ��߱���
 * ��������ʦ������
 * */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import java.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.logging.log4j.Logger;

import com.huanmeng.mod.blocks.Food;
import com.huanmeng.mod.proxies.CommonProxy;
import com.huanmeng.mod.utils.Reference;

import net.minecraft.crash.CrashReport;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ReportedException;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION ,updateJSON = "https://rplay123.github.io/update.json")
public class Xijun {
	public static void main(String[] args) throws InterruptedException{
		JFrame ck = new JFrame("warn");
		ck.setBounds(400,330,780,150);
		/**ck.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 * if use this game can't load.*/
		JLabel zi = new JLabel("warn");
		zi.setText("<html><body>hi,Bacteria mod has detected that some mods have been blacklisted.You can delete it or take it out of the blacklist an restart the game<br>Bacteria mod\u0020\u68c0\u6d4b\u5230\u67d0\u4e9b\u006d\u006f\u0064\u88ab\u5217\u5165\u9ed1\u540d\u5355\uff0c\u4f60\u53ef\u4ee5\u628a\u5b83\u5220\u9664\u6389\uff0c\u6216\u8005\u4ece\u9ed1\u540d\u5355\u4e2d\u79fb\u9664<br>.minecraft/config/xijun.cfg line:5<br>20\u79d2\u540e\u6e38\u620f\u5c06\u5d29\u6e83</body></html>");
		ck.add(zi);
		ck.setVisible(true);
		Thread.sleep(20000);
	}
	public static final CreativeTabs tabBacteria = new TabBacteria("xijun.bacteria");
	public static final CreativeTabs tabApple = new TabApple("xijun.apple");
	
	@Instance
	public static Xijun instance;
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	public static Logger logger;
	public static int speed;
	public static boolean randomize;
	public static String isolation;
	public static ArrayList<Food> blacklist;
	public static boolean jam_all;
	public static ArrayList<Integer> jamcolonies = new ArrayList<Integer>();
	@EventHandler
	public static void Init(FMLInitializationEvent event) {}
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event) {}
	@EventHandler
	  public void preInit(FMLPreInitializationEvent event) throws InterruptedException {
		logger = event.getModLog();
		proxy.preInit(event);
	    List<String> modIds = (List)Arrays.asList(ModConfig.ban).stream().filter(modId -> !modId.equals("Xijunmod")).filter(Loader::isModLoaded).collect(Collectors.toList());
	    if (!modIds.isEmpty()) {
	      CrashReport cr = CrashReport.makeCrashReport(new IllegalAccessError(), 
	          String.format("BanModList: %s", new Object[] { modIds.toString() }));
			  main(null);
	      throw new ReportedException(cr);
	    } 
	  }
	  @Config(modid = "xijun")
	  public static final class ModConfig {
	    @Comment({"BanModList(set)"})
	    public static String[] ban = { "bathappymod" };
	  }
	  @Config(modid = "bathappymod")
	  public static final class ModConfig1 {
	    @Comment({"BanModList(set)"})
	    public static String[] ban = { "bathappymod" };
	  }
	  
	  
}