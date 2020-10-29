package info.ankurpandya.roughlocation;

/**
 * Create by Ankur @ Worktable.sg
 */
public interface LocationListener {
    void onLocationReceived(RoughLocation roughLocation);

    void onLocationFailed();

    void onNetworkError();
}
