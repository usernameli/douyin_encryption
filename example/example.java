

public class DouYin_test {
	private static String min_time = "";
	public static void main(String[] args) throws IOException {
		DouYin_test test = new DouYin_test();
		
		String urlStr = "https://api.huoshan.com/hotsoon/user/relation/upload_contact/?live_sdk_version=261&iid=45894213540&device_id=55867298418&ac=wifi&channel=Yunosfolder&aid=1112&app_name=live_stream&version_code=261&version_name=2.6.1&device_platform=android&ssmix=a&device_type=Nexus+5&device_brand=google&os_api=19&os_version=4.4.3&uuid=359250051841177&openudid=4a303e11a75ed6e1&manifest_version_code=261&resolution=1080*1776&dpi=480&update_version_code=2610&ts=1539570259"+"&contact="+contact;
		urlStr = test.retrunAcAnd(urlStr,null);
		System.out.println(urlStr);
	}
	
	public static String retrunAcAnd(String urls,String contactInfo){
		String url =urls.indexOf("?")>-1?urls.substring(urls.indexOf("?")+1):urls;
		if (contactInfo!=null) {
			url = url + "&contact="+contactInfo;
		}
		String[] splitStrs = url.split("&");
		if(splitStrs!=null&&splitStrs.length>0){
			Map<String,String> map = new HashMap<String,String>();
			map.put("s", "xxxxxxxx");
			for(String splitStr:splitStrs){
				String[] keyAndValue = splitStr.split("=");
				if(keyAndValue!=null&&keyAndValue.length==2){
					map.put(keyAndValue[0],keyAndValue[1]);
				}else{
					throw new RuntimeException("param:"+splitStr+" is wrong");
				}
			}
			char[] time1=null;
			char[] time2=null;
			if(map.containsKey("ts")){
				String time=Long.toHexString(Long.parseLong(map.get("ts")));
				time1=getTime1(time).toCharArray();
				time2=getTime2(time).toCharArray();
			}else{
				throw new RuntimeException("ts: is null");
			}
			String md5 = MD5param(map);
			char[] asStr = md5.substring(0, 8).toCharArray();
			char[] cpStr = md5.substring(md5.length()-8).toCharArray();
			StringBuffer assb = new StringBuffer(urls);
			assb=assb.append("&as=a2");
			for(int i=0;i<8;i++){
				assb=assb.append(asStr[i]).append(time1[i]);
			}
			assb=assb.append("&cp=");
			for(int i=0;i<8;i++){
				assb=assb.append(time2[i]).append(cpStr[i]);
			}
			assb = assb.append("e2");
			return assb.toString();
		}else{
			throw new RuntimeException("url is wrong");
		}

	}
	private static String getTime1(String timeHex) {
		char[] chars = timeHex.toCharArray();
		StringBuilder stringBuilder = new StringBuilder();
		return stringBuilder.append(chars[0]).append(chars[4]).append(chars[2]).append(chars[7]).append(chars[6]).append(chars[1]).append(chars[5]).append(chars[3]).toString();
	}
	private static String getTime2(String timeHex) {
		char[] chars = timeHex.toCharArray();
		StringBuilder stringBuilder = new StringBuilder();
		return stringBuilder.append(chars[4]).append(chars[6]).append(chars[1]).append(chars[0]).append(chars[7]).append(chars[3]).append(chars[2]).append(chars[5]).toString();
	}
	private static String MD5param(Map<String,String>map){
		boolean md5Flag = false;
		if(map.containsKey("ts")){
			Long tsNo=Long.parseLong(map.get("ts"));
			if(tsNo%2==0){
				md5Flag=true;
			}
		}
		Set<String> paramSte = map.keySet();
		StringBuffer sb = new StringBuffer();
		if(paramSte!=null&&paramSte.size()>0){
			List<String> keys = new ArrayList<>();
			keys.addAll(paramSte);
			Collections.sort(keys);
			for(String key:keys){
				sb.append(map.get(key).replace("+", "a"));
				System.out.println(key + " = " + map.get(key));
				
			}
			System.out.println(sb.toString());
	
			if(md5Flag){
				return MD5Utils.md5(sb.toString());
			}else{
				return MD5Utils.md5(MD5Utils.md5(sb.toString()));
			}
		}else{
			return "";
		}
	}
}
