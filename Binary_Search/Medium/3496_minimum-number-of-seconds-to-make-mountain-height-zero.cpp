class Solution {
public:
    bool can(long long time, int mountainHeight, vector<int>& workerTimes) {
        long long total = 0;
        for (int t : workerTimes) {
            long long x = (sqrt(1 + 8.0 * time / t) - 1) / 2;
            total += x;
            if (total >= mountainHeight) return true;
        }
        return false;
    }

    long long minNumberOfSeconds(int mountainHeight, vector<int>& workerTimes) {
        long long left = 0, right = 1e18;

        while (left < right) {
            long long mid = left + (right - left) / 2;
            if (can(mid, mountainHeight, workerTimes))
                right = mid;
            else
                left = mid + 1;
        }

        return left;
    }
};