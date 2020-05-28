/*
 * Copyright 2014 Hieu Rocker
 *
 * Licensed under the Apache License, Version 2.0 (the "License";
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.leo.impro.utils;


import com.leo.impro.R;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hieu Rocker (rockerhieu@gmail.com
 */
public class Lemoji {

    //这是通过描述找出表情。
    public static Map<String, Integer> findInteger = new HashMap<>();
    public static Map<Integer, String> findString = new HashMap<>();
    /*
     * 此项目是结合2个项目基础上修改的。为了兼容之前的方法 这里用2个map实现效果
     * 有待后期优化
     * */

    static {
        findString.put(0x1f604, "[IOS表情1]");
        findString.put(0x1f603, "[IOS表情2]");
        findString.put(0x1f600, "[IOS表情3]");
        findString.put(0x1f60a, "[IOS表情4]");
        findString.put(0x263a, "[IOS表情5]");
        findString.put(0x1f609, "[IOS表情6]");
        findString.put(0x1f60d, "[IOS表情7]");
        findString.put(0x1f618, "[IOS表情8]");
        findString.put(0x1f61a, "[IOS表情9]");
        findString.put(0x1f617, "[IOS表情10]");
        findString.put(0x1f619, "[IOS表情11]");
        findString.put(0x1f61c, "[IOS表情12]");
        findString.put(0x1f61d, "[IOS表情13]");
        findString.put(0x1f61b, "[IOS表情14]");
        findString.put(0x1f633, "[IOS表情15]");
        findString.put(0x1f601, "[IOS表情16]");
        findString.put(0x1f614, "[IOS表情17]");
        findString.put(0x1f60c, "[IOS表情18]");
        findString.put(0x1f612, "[IOS表情19]");
        findString.put(0x1f61e, "[IOS表情20]");
        findString.put(0x1f623, "[IOS表情21]");
        findString.put(0x1f622, "[IOS表情22]");
        findString.put(0x1f602, "[IOS表情23]");
        findString.put(0x1f62d, "[IOS表情24]");
        findString.put(0x1f62a, "[IOS表情25]");
        findString.put(0x1f625, "[IOS表情26]");
        findString.put(0x1f630, "[IOS表情27]");
        findString.put(0x1f605, "[IOS表情28]");
        findString.put(0x1f613, "[IOS表情29]");
        findString.put(0x1f629, "[IOS表情30]");
        findString.put(0x1f62b, "[IOS表情31]");
        findString.put(0x1f628, "[IOS表情32]");
        findString.put(0x1f631, "[IOS表情33]");
        findString.put(0x1f620, "[IOS表情34]");
        findString.put(0x1f621, "[IOS表情35]");
        findString.put(0x1f624, "[IOS表情36]");
        findString.put(0x1f616, "[IOS表情37]");
        findString.put(0x1f606, "[IOS表情38]");
        findString.put(0x1f60b, "[IOS表情39]");
        findString.put(0x1f637, "[IOS表情40]");
        findString.put(0x1f60e, "[IOS表情41]");
        findString.put(0x1f634, "[IOS表情42]");
        findString.put(0x1f635, "[IOS表情43]");
        findString.put(0x1f632, "[IOS表情44]");
        findString.put(0x1f61f, "[IOS表情45]");
        findString.put(0x1f626, "[IOS表情46]");
        findString.put(0x1f627, "[IOS表情47]");
        findString.put(0x1f608, "[IOS表情48]");
        findString.put(0x1f47f, "[IOS表情49]");
        findString.put(0x1f62e, "[IOS表情50]");
        findString.put(0x1f62c, "[IOS表情51]");
        findString.put(0x1f610, "[IOS表情52]");
        findString.put(0x1f615, "[IOS表情53]");
        findString.put(0x1f62f, "[IOS表情54]");
        findString.put(0x1f636, "[IOS表情55]");
        findString.put(0x1f607, "[IOS表情56]");
        findString.put(0x1f60f, "[IOS表情57]");
        findString.put(0x1f611, "[IOS表情58]");


        //地球表亲
        findString.put(0x1f31e, "[地球表情1]");
        findString.put(0x1f31d, "[地球表情2]");
        findString.put(0x1f31a, "[地球表情3]");
        findString.put(0x1f311, "[地球表情4]");
        findString.put(0x1f312, "[地球表情5]");
        findString.put(0x1f313, "[地球表情6]");
        findString.put(0x1f314, "[地球表情7]");
        findString.put(0x1f315, "[地球表情8]");
        findString.put(0x1f316, "[地球表情9]");
        findString.put(0x1f317, "[地球表情10]");
        findString.put(0x1f318, "[地球表情11]");
        findString.put(0x1f31c, "[地球表情12]");
        findString.put(0x1f31b, "[地球表情13]");
        findString.put(0x1f319, "[地球表情14]");
        findString.put(0x1f30d, "[地球表情15]");
        findString.put(0x1f30e, "[地球表情16]");
        findString.put(0x1f30f, "[地球表情17]");

        //逗比表情
        findString.put(R.drawable.dding1, "[逗比表情1]");
        findString.put(R.drawable.dding2, "[逗比表情2]");
        findString.put(R.drawable.dding3, "[逗比表情3]");
        findString.put(R.drawable.dding4, "[逗比表情4]");
        findString.put(R.drawable.dding5, "[逗比表情5]");
        findString.put(R.drawable.dding6, "[逗比表情6]");
        findString.put(R.drawable.dding7, "[逗比表情7]");
        findString.put(R.drawable.dding8, "[逗比表情8]");
        findString.put(R.drawable.dding9, "[逗比表情9]");
        findString.put(R.drawable.dding10, "[逗比表情10]");
        findString.put(R.drawable.dding11, "[逗比表情11]");
        findString.put(R.drawable.dding12, "[逗比表情12]");
        findString.put(R.drawable.dding13, "[逗比表情13]");
        findString.put(R.drawable.dding14, "[逗比表情14]");
        findString.put(R.drawable.dding15, "[逗比表情15]");
        findString.put(R.drawable.dding16, "[逗比表情16]");
        findString.put(R.drawable.dding17, "[逗比表情17]");
        findString.put(R.drawable.dding18, "[逗比表情18]");
        findString.put(R.drawable.dding19, "[逗比表情19]");
        findString.put(R.drawable.dding20, "[逗比表情20]");
    }

    static {

        findInteger.put("[IOS表情1]", 0x1f604);
        findInteger.put("[IOS表情2]", 0x1f603);
        findInteger.put("[IOS表情3]", 0x1f600);
        findInteger.put("[IOS表情4]", 0x1f60a);
        findInteger.put("[IOS表情5]", 0x263a);
        findInteger.put("[IOS表情6]", 0x1f609);
        findInteger.put("[IOS表情7]", 0x1f60d);
        findInteger.put("[IOS表情8]", 0x1f618);
        findInteger.put("[IOS表情9]", 0x1f61a);
        findInteger.put("[IOS表情10]", 0x1f617);
        findInteger.put("[IOS表情11]", 0x1f619);
        findInteger.put("[IOS表情12]", 0x1f61c);
        findInteger.put("[IOS表情13]", 0x1f61d);
        findInteger.put("[IOS表情14]", 0x1f61b);
        findInteger.put("[IOS表情15]", 0x1f633);
        findInteger.put("[IOS表情16]", 0x1f601);
        findInteger.put("[IOS表情17]", 0x1f614);
        findInteger.put("[IOS表情18]", 0x1f60c);
        findInteger.put("[IOS表情19]", 0x1f612);
        findInteger.put("[IOS表情20]", 0x1f61e);
        findInteger.put("[IOS表情21]", 0x1f623);
        findInteger.put("[IOS表情22]", 0x1f622);
        findInteger.put("[IOS表情23]", 0x1f602);
        findInteger.put("[IOS表情24]", 0x1f62d);
        findInteger.put("[IOS表情25]", 0x1f62a);
        findInteger.put("[IOS表情26]", 0x1f625);
        findInteger.put("[IOS表情27]", 0x1f630);
        findInteger.put("[IOS表情28]", 0x1f605);
        findInteger.put("[IOS表情29]", 0x1f613);
        findInteger.put("[IOS表情30]", 0x1f629);
        findInteger.put("[IOS表情31]", 0x1f62b);
        findInteger.put("[IOS表情32]", 0x1f628);
        findInteger.put("[IOS表情33]", 0x1f631);
        findInteger.put("[IOS表情34]", 0x1f620);
        findInteger.put("[IOS表情35]", 0x1f621);
        findInteger.put("[IOS表情36]", 0x1f624);
        findInteger.put("[IOS表情37]", 0x1f616);
        findInteger.put("[IOS表情38]", 0x1f606);
        findInteger.put("[IOS表情39]", 0x1f60b);
        findInteger.put("[IOS表情40]", 0x1f637);
        findInteger.put("[IOS表情41]", 0x1f60e);
        findInteger.put("[IOS表情42]", 0x1f634);
        findInteger.put("[IOS表情43]", 0x1f635);
        findInteger.put("[IOS表情44]", 0x1f632);
        findInteger.put("[IOS表情45]", 0x1f61f);
        findInteger.put("[IOS表情46]", 0x1f626);
        findInteger.put("[IOS表情47]", 0x1f627);
        findInteger.put("[IOS表情48]", 0x1f608);
        findInteger.put("[IOS表情49]", 0x1f47f);
        findInteger.put("[IOS表情50]", 0x1f62e);
        findInteger.put("[IOS表情51]", 0x1f62c);
        findInteger.put("[IOS表情52]", 0x1f610);
        findInteger.put("[IOS表情53]", 0x1f615);
        findInteger.put("[IOS表情54]", 0x1f62f);
        findInteger.put("[IOS表情55]", 0x1f636);
        findInteger.put("[IOS表情56]", 0x1f607);
        findInteger.put("[IOS表情57]", 0x1f60f);
        findInteger.put("[IOS表情58]", 0x1f611);


        //地球表情

        findInteger.put("[地球表情1]", 0x1f31e);
        findInteger.put("[地球表情2]", 0x1f31d);
        findInteger.put("[地球表情3]", 0x1f31a);
        findInteger.put("[地球表情4]", 0x1f311);
        findInteger.put("[地球表情5]", 0x1f312);
        findInteger.put("[地球表情6]", 0x1f313);
        findInteger.put("[地球表情7]", 0x1f314);
        findInteger.put("[地球表情8]", 0x1f315);
        findInteger.put("[地球表情9]", 0x1f316);
        findInteger.put("[地球表情10]", 0x1f317);
        findInteger.put("[地球表情11]", 0x1f318);
        findInteger.put("[地球表情12]", 0x1f31c);
        findInteger.put("[地球表情13]", 0x1f31b);
        findInteger.put("[地球表情14]", 0x1f319);
        findInteger.put("[地球表情15]", 0x1f30d);
        findInteger.put("[地球表情16]", 0x1f30e);
        findInteger.put("[地球表情17]", 0x1f30f);


        //自定义表情 逗比表情
        findInteger.put("[逗比表情1]", R.drawable.dding1);
        findInteger.put("[逗比表情2]", R.drawable.dding2);
        findInteger.put("[逗比表情3]", R.drawable.dding3);
        findInteger.put("[逗比表情4]", R.drawable.dding4);
        findInteger.put("[逗比表情5]", R.drawable.dding5);
        findInteger.put("[逗比表情6]", R.drawable.dding6);
        findInteger.put("[逗比表情7]", R.drawable.dding7);
        findInteger.put("[逗比表情8]", R.drawable.dding8);
        findInteger.put("[逗比表情9]", R.drawable.dding9);
        findInteger.put("[逗比表情10]", R.drawable.dding10);
        findInteger.put("[逗比表情11]", R.drawable.dding11);
        findInteger.put("[逗比表情12]", R.drawable.dding12);
        findInteger.put("[逗比表情13]", R.drawable.dding13);
        findInteger.put("[逗比表情14]", R.drawable.dding14);
        findInteger.put("[逗比表情15]", R.drawable.dding15);
        findInteger.put("[逗比表情16]", R.drawable.dding16);
        findInteger.put("[逗比表情17]", R.drawable.dding17);
        findInteger.put("[逗比表情18]", R.drawable.dding18);
        findInteger.put("[逗比表情19]", R.drawable.dding19);
        findInteger.put("[逗比表情20]", R.drawable.dding20);

    }


    //人表情
    public static final Integer[] DATA = new Integer[]{
            0x1f604,
            0x1f603,
            0x1f600,
            0x1f60a,
            0x263a,
            0x1f609,
            0x1f60d,
            0x1f618,
            0x1f61a,
            0x1f617,
            0x1f619,
            0x1f61c,
            0x1f61d,
            0x1f61b,
            0x1f633,
            0x1f601,
            0x1f614,
            0x1f60c,
            0x1f612,
            0x1f61e,
            0x1f623,
            0x1f622,
            0x1f602,
            0x1f62d,
            0x1f62a,
            0x1f625,
            0x1f630,
            0x1f605,
            0x1f613,
            0x1f629,
            0x1f62b,
            0x1f628,
            0x1f631,
            0x1f620,
            0x1f621,
            0x1f624,
            0x1f616,
            0x1f606,
            0x1f60b,
            0x1f637,
            0x1f60e,
            0x1f634,
            0x1f635,
            0x1f632,
            0x1f61f,
            0x1f626,
            0x1f627,
            0x1f608,
            0x1f47f,
            0x1f62e,
            0x1f62c,
            0x1f610,
            0x1f615,
            0x1f62f,
            0x1f636,
            0x1f607,
            0x1f60f,
            0x1f611
    };

    //太阳地球表情
    public static final Integer[] SUNDATA = new Integer[]{
            0x1f31e,
            0x1f31d,
            0x1f31a,
            0x1f311,
            0x1f312,
            0x1f313,
            0x1f314,
            0x1f315,
            0x1f316,
            0x1f317,
            0x1f318,
            0x1f31c,
            0x1f31b,
            0x1f319,
            0x1f30d,
            0x1f30e,
            0x1f30f
    };

    //自定义表情
    public static final Integer[] MYFACE = new Integer[]{
            R.drawable.dding1,
            R.drawable.dding2,
            R.drawable.dding3,
            R.drawable.dding4,
            R.drawable.dding5,
            R.drawable.dding6,
            R.drawable.dding7,
            R.drawable.dding8,
            R.drawable.dding9,
            R.drawable.dding10,
            R.drawable.dding11,
            R.drawable.dding12,
            R.drawable.dding13,
            R.drawable.dding14,
            R.drawable.dding15,
            R.drawable.dding16,
            R.drawable.dding17,
            R.drawable.dding18,
            R.drawable.dding19,
            R.drawable.dding20
    };


}
