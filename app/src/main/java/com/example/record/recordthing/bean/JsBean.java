package com.example.record.recordthing.bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * 项目名：RecordMatter
 * 包名：com.example.recordmatter.bean
 * 文件名：JsonBean
 * 创建者：Gyk
 * 创建时间：2019/4/1 16:22
 * 描述：  TODO
 */

public class JsBean implements IPickerViewData {


    /**
     * code :
     * name : 全部
     * cityList : [{"code":"","name":"全部","areaList":[{"code":"","name":"全部"}]}]
     */

    private String code;
    private String name;
    private List<CityListBean> cityList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityListBean> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityListBean> cityList) {
        this.cityList = cityList;
    }

    // 实现 IPickerViewData 接口，
    // 这个用来显示在PickerView上面的字符串，
    // PickerView会通过IPickerViewData获取getPickerViewText方法显示出来。
    @Override
    public String getPickerViewText() {
        return this.name;
    }


    public static class CityListBean {
        /**
         * code :
         * name : 全部
         * areaList : [{"code":"","name":"全部"}]
         */

        private String code;
        private String name;
        private List<AreaListBean> areaList;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<AreaListBean> getAreaList() {
            return areaList;
        }

        public void setAreaList(List<AreaListBean> areaList) {
            this.areaList = areaList;
        }

        public static class AreaListBean {
            /**
             * code :
             * name : 全部
             */

            private String code;
            private String name;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}