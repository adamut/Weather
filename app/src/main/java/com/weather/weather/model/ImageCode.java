package com.weather.weather.model;


import com.weather.weather.R;

public class ImageCode {

    private int[] weatherImages = {R.drawable.p501, R.drawable.p501, R.drawable.p502, R.drawable.p503, R.drawable.p504,
            R.drawable.p505, R.drawable.p506, R.drawable.p507, R.drawable.p508, R.drawable.p509, R.drawable.p510,
            R.drawable.p511, R.drawable.p600, R.drawable.p601, R.drawable.p602n, R.drawable.p603, R.drawable.p604,
            R.drawable.p605, R.drawable.p606, R.drawable.p607, R.drawable.p608, R.drawable.p609, R.drawable.p610,
            R.drawable.p800d, R.drawable.p800n, R.drawable.p801, R.drawable.p801d, R.drawable.p800n,
            R.drawable.p804, R.drawable.p805n, R.drawable.p806, R.drawable.p807n, R.drawable.p808, R.drawable.p809n,
            R.drawable.p904, R.drawable.p905, R.drawable.p701};

    private int[] backgroundImages = {R.drawable.cloudy, R.drawable.foggy, R.drawable.raining, R.drawable.light_rain, R.drawable.snow,
            R.drawable.sunn, R.drawable.windy, R.drawable.thuderstorm,R.drawable.sunny};

    public int searchBackgroundValue(int id) {
        if (id >= 200 && id <= 299)
            return R.drawable.thuderstorm;
        if (id >= 300 && id <= 499)
            return R.drawable.raining;
        if (id == 500)
            return R.drawable.light_rain;
        if (id == 501)
            return R.drawable.raining;
        if (id >= 502 && id <= 510)
            return R.drawable.raining;
        if (id >= 511 && id <= 519)
            return R.drawable.raining;
        if (id >= 520 && id <= 599)
            return R.drawable.raining;

        if (id == 600)
            return R.drawable.snow;

        if (id == 601)
            return R.drawable.snow;

        if (id == 602)
            return R.drawable.snow;

        if (id == 611)
            return R.drawable.snow;

        if (id == 612)
            return R.drawable.snow;

        if (id == 615)
            return R.drawable.snow;

        if (id == 616)
            return R.drawable.snow;

        if (id == 620)
            return R.drawable.snow;

        if (id == 621)
            return R.drawable.snow;

        if (id == 622)
            return R.drawable.snow;
        if (id >= 623 && id <= 700)
            return R.drawable.snow;

        if (id >= 701 && id <= 710)
            return R.drawable.foggy;

        if (id >= 711 && id <= 720)
            return R.drawable.foggy;

        if (id >= 721 && id <= 730)
            return R.drawable.foggy;

        if (id >= 731 && id <= 740)
            return R.drawable.foggy;

        if (id >= 741 && id <= 750)
            return R.drawable.foggy;

        if (id >= 751 && id <= 760)
            return R.drawable.foggy;

        if (id == 761)
            return R.drawable.foggy;

        if (id >= 762 && id <= 770)
            return R.drawable.foggy;

        if (id >= 771 && id <= 780)
            return R.drawable.foggy;

        if (id >= 781 && id <= 799)
            return R.drawable.foggy;

        if (id == 800)
            return R.drawable.sunn;

        if (id == 801)
            return R.drawable.sunn;

        if (id == 802)
            return R.drawable.cloudy;

        if (id == 803)
            return R.drawable.cloudy;

        if (id >= 804 && id <= 903)
            return R.drawable.cloudy;

        if (id == 904)
            return R.drawable.cloudy;

        if (id >= 905 && id <= 970)
            return R.drawable.sunn;

        return R.drawable.sunn;
    }

    public int searchIconValue(int id) {
        if (id >= 200 && id <= 299)
            return R.drawable.p510;
        if (id >= 300 && id <= 499)
            return R.drawable.p507;
        if (id == 500)
            return R.drawable.p505;
        if (id == 501)
            return R.drawable.p506;
        if (id >= 502 && id <= 510)
            return R.drawable.p501;
        if (id >= 511 && id <= 519)
            return R.drawable.p503;
        if (id >= 520 && id <= 599)
            return R.drawable.p501;

        if (id == 600)
            return R.drawable.p605;

        if (id == 601)
            return R.drawable.p606;

        if (id == 602)
            return R.drawable.p608;

        if (id == 611)
            return R.drawable.p605;

        if (id == 612)
            return R.drawable.p605;

        if (id == 615)
            return R.drawable.p603;

        if (id == 616)
            return R.drawable.p603;

        if (id == 620)
            return R.drawable.p603;

        if (id == 621)
            return R.drawable.p607;

        if (id == 622)
            return R.drawable.p607;
        if (id >= 623 && id <= 700)
            return R.drawable.p607;

        if (id >= 701 && id <= 710)
            return R.drawable.p701;

        if (id >= 711 && id <= 720)
            return R.drawable.p701;

        if (id >= 721 && id <= 730)
            return R.drawable.p701;

        if (id >= 731 && id <= 740)
            return R.drawable.p701;

        if (id >= 741 && id <= 750)
            return R.drawable.p701;

        if (id >= 751 && id <= 760)
            return R.drawable.p701;

        if (id == 761)
            return R.drawable.p701;

        if (id >= 762 && id <= 770)
            return R.drawable.p701;

        if (id >= 771 && id <= 780)
            return R.drawable.p701;

        if (id >= 781 && id <= 799)
            return R.drawable.p701;

        if (id == 800)
            return R.drawable.p800d;

        if (id == 801)
            return R.drawable.p801d;

        if (id == 802)
            return R.drawable.p808;

        if (id == 803)
            return R.drawable.p806;

        if (id >= 804 && id <= 903)
            return R.drawable.p804;

        if (id == 904)
            return R.drawable.p904;

        if (id >= 905 && id <= 970)
            return R.drawable.p905;

        return R.drawable.p800d;
    }

    public ImageCode() {
    }

    public ImageCode(int[] weatherImages) {
        this.weatherImages = weatherImages;
    }
}
