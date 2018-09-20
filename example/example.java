

private static String getParams(String data, int ts) {
        String timeHex = Integer.toHexString(ts);
        System.out.println("timeHex = " + timeHex);
        //String str1 = "583babdc";
        String str1 = getTime1(timeHex);
        System.out.println("str1 = " + str1);

        String str2 = getTime2(timeHex);
        System.out.println("str2 = " + str2);
        //String str2 = "8ab5bc3d";
        String str3 = "a1";

        // String dir = MD5Utils.md5(MD5Utils.md5(data));
        String str = MD5Utils.md5(data);
        if ((ts&1)==1) {
			str = MD5Utils.md5(str);
        }
}