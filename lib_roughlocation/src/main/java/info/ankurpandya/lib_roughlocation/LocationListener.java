package info.ankurpandya.lib_roughlocation;

/**
 * Create by Ankur @ Worktable.sg
 */
public interface LocationListener {
    void onLocationReceived(RoughLocation roughLocation);

    void onLocationFailed();

    void onNetworkError();
}
