package info.ankurpandya.roughlocation;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Create by Ankur @ Worktable.sg
 */
public class RoughLocation implements Serializable, Parcelable {

    @SerializedName("status")
    private String status;

    @SerializedName("country")
    private String country;

    @SerializedName("countryCode")
    private String countryCode;

    @SerializedName("region")
    private String region;

    @SerializedName("regionName")
    private String regionName;

    @SerializedName("city")
    private String city;

    @SerializedName("zip")
    private String zip;

    @SerializedName("lat")
    private double latitude;

    @SerializedName("lon")
    private double longitude;

    @SerializedName("timezone")
    private String timezone;

    @SerializedName("isp")
    private String isp;

    @SerializedName("org")
    private String organisation;

    @SerializedName("as")
    private String autonomousSystem;

    @SerializedName("query")
    private String query;

    public RoughLocation() {
        //No default construction
    }

    protected RoughLocation(Parcel in) {
        status = in.readString();
        country = in.readString();
        countryCode = in.readString();
        region = in.readString();
        regionName = in.readString();
        city = in.readString();
        zip = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        timezone = in.readString();
        isp = in.readString();
        organisation = in.readString();
        autonomousSystem = in.readString();
        query = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(country);
        dest.writeString(countryCode);
        dest.writeString(region);
        dest.writeString(regionName);
        dest.writeString(city);
        dest.writeString(zip);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(timezone);
        dest.writeString(isp);
        dest.writeString(organisation);
        dest.writeString(autonomousSystem);
        dest.writeString(query);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RoughLocation> CREATOR = new Creator<RoughLocation>() {
        @Override
        public RoughLocation createFromParcel(Parcel in) {
            return new RoughLocation(in);
        }

        @Override
        public RoughLocation[] newArray(int size) {
            return new RoughLocation[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getRegion() {
        return region;
    }

    public String getRegionName() {
        return regionName;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getIsp() {
        return isp;
    }

    public String getOrganisation() {
        return organisation;
    }

    public String getAutonomousSystem() {
        return autonomousSystem;
    }

    public String getQuery() {
        return query;
    }


    @Override
    public String toString() {
        return "RoughLocation{" +
                "status='" + status + '\'' +
                ", country='" + country + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", region='" + region + '\'' +
                ", regionName='" + regionName + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", timezone='" + timezone + '\'' +
                ", isp='" + isp + '\'' +
                ", organisation='" + organisation + '\'' +
                ", autonomousSystem='" + autonomousSystem + '\'' +
                ", query='" + query + '\'' +
                '}';
    }
}
