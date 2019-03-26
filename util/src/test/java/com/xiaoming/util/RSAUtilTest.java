package com.xiaoming.util;

import org.junit.Test;

/**
 * 测试签名验签；
 */
public class RSAUtilTest {
    static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDdLIh15vZnXlbKVzVjVKQNZ/+q\n" +
            "5uOzssX5VO1owcBsdBzVXRLPDypWrXs60xjqYYXzxWdYiuG6IrXePbnhHDpyRkrS\n" +
            "fFB/0qg5SFpqp+TFP68T/su1+2Q0SrJctRGGLU/Kk7i1+5uGsr6aQwsEymiA6z++\n" +
            "dAsnzVKKuDTTa1nDbwIDAQAB";

    static final String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAN0siHXm9mdeVspX\n" +
            "NWNUpA1n/6rm47OyxflU7WjBwGx0HNVdEs8PKlatezrTGOphhfPFZ1iK4boitd49\n" +
            "ueEcOnJGStJ8UH/SqDlIWmqn5MU/rxP+y7X7ZDRKsly1EYYtT8qTuLX7m4ayvppD\n" +
            "CwTKaIDrP750CyfNUoq4NNNrWcNvAgMBAAECgYAckHPPRlTsZLKwzzKF8Zh+YsAS\n" +
            "Ql+GBcCrwtwme19uolBW/f4/36WILD+OCVVeb9ns8cG3HbipYXI7kZ1ABXVGr14t\n" +
            "uADa/w2TBuOHajUD5nVCShZpObevl9Bcpf2xC+hP6uODXNHUw1j0pbE5j6D+LTwa\n" +
            "K1KTkULe9+6uPdmkgQJBAPuod0zqkwnKgT+YfTtckq2ran75esujSTNIxrmDe/KY\n" +
            "E4JyLQhByNjPvuhzJHEWLWaJyp15Cmfwy06KsbVqaGUCQQDg/WxrcuZ/DJjtdYR6\n" +
            "rIy398h9n1Fxim+0NS+uTAMp+xug5kkPvtXkKiOAKY/9igt3VW0UnMt+1eJpvFWY\n" +
            "qR1DAkEArmb/1pQyNnTawaICF+N921GSSlDG4t1UW3AyGFqgyWzPCHXr827zrB28\n" +
            "5gb12FFQQWF4w00hAwuDOfkwUtNdaQJAOEYqwBVVIaH8/ifOowBVkg4fz4vxyvxd\n" +
            "RC2pZ2cLw4xTNMNkstOAnDkN+BhtM3U7uPVc+zLckzTcFKZOoohqEQJBANXsfCoi\n" +
            "vr9k1bspW1WBtjrh3ik/Er4JjQ4c0MbtJ3FdE4w3faqunpmJ5KyCpz2yF05JRRZP\n" +
            "muMVZ6XAoYILKZE=";

    @Test
    public void signTest(){
        String text = "xiaoming";
        String sign = RSAUtil.sign(text, privateKey);
        System.out.println(sign);
        System.out.println(RSAUtil.verify(text, sign, publicKey));
    }
}
