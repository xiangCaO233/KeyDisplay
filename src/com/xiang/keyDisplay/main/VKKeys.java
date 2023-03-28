package com.xiang.keyDisplay.main;

import java.util.HashMap;

public class VKKeys {
    public static final HashMap<Integer, String> data = registerData();

    private static HashMap<Integer, String> registerData() {
        HashMap<Integer, String> keysMap = new HashMap<>();
        for (int i = 112; i < 124; i++) {//F1-F12[12个键](12)
            //F1-112......
            keysMap.put(i, ("F" + (i - 111)));
        }
        for (int i = 48; i < 58; i++) {//大键盘0-9[10个键](22)
            //48-0......
            keysMap.put(i, (String.valueOf(i - 48)));
        }
        for (int i = 96; i < 106; i++) {//小键盘0-9[10个键](32)
            //N0-96......
            keysMap.put(i, ("N" + (i - 96)));
        }
        for (int i = 65; i < 91; i++) {//A-Z[26个键](58)
            //A-65
            keysMap.put(i, (String.valueOf((char) i)));
        }
        //[1个键](59)
        keysMap.put(27, "ESC");
        //[12个键](71)
        keysMap.put(192, "~");
        keysMap.put(189, "-");
        keysMap.put(187, "=");
        keysMap.put(8, "BackS");
        keysMap.put(44, "PrtSc");
        keysMap.put(145, "ScrLk");
        keysMap.put(19, "Pause");
        keysMap.put(45, "Insert");
        keysMap.put(36, "Home");
        keysMap.put(144, "NumLk");
        keysMap.put(111, "Divide");
        keysMap.put(106, "Multi");
        keysMap.put(109, "Sub");
        //[9个键](80)
        keysMap.put(9, "Tab");
        keysMap.put(219, "[");
        keysMap.put(221, "]");
        keysMap.put(220, "\\");
        keysMap.put(46, "DEL");
        keysMap.put(35, "END");
        keysMap.put(33, "PgUp");
        keysMap.put(34, "PgDn");
        keysMap.put(107, "Add");
        //[4个键](84)
        keysMap.put(20, "CapsLk");
        keysMap.put(186, ";");
        keysMap.put(222, "\'");
        keysMap.put(13, "Enter");
        //[5个键](89)
        keysMap.put(160, "LShift");
        keysMap.put(188, ",");
        keysMap.put(190, ".");
        keysMap.put(191, "/");
        keysMap.put(161, "RShift");
        //[12个键](101)
        keysMap.put(38, "↑");
        keysMap.put(162, "LCtrl");
        keysMap.put(91, "Win");
        keysMap.put(164, "LAlt");
        keysMap.put(32, "Space");
        keysMap.put(165, "RAlt");
        keysMap.put(93, "Apps");
        keysMap.put(163, "RCtrl");
        keysMap.put(37, "←");
        keysMap.put(40, "↓");
        keysMap.put(39, "→");
        keysMap.put(110, "N.");
        keysMap.put(1, "LMB");
        keysMap.put(2, "RMB");
        keysMap.put(3, "MMB");
        return keysMap;
    }

    public static int KeySearch(String KeyName) {
        for (int i = 0; i < data.size(); i++) {
            for (int j : data.keySet()) {
                if ((KeyName).equalsIgnoreCase((data.get(j)))) {
                    return j;
                }
            }
        }
        return -1;
    }
}
