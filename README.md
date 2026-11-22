# ALI X CHEATS VPN

Android starter app for the ALI X CHEATS VPN brand. Includes a black/neon-green UI and five selectable server placeholders.

## Build APK in GitHub Actions (phone-friendly)
1. Create a GitHub account and a new repository named `ALI-X-CHEATS-VPN`.
2. Upload the contents of this folder to the repository (not the enclosing folder).
3. Open the repository's **Actions** tab and run **Build ALI X CHEATS VPN APK** (or wait for the first push build).
4. Open the completed workflow run, scroll to **Artifacts**, and download `ALI-X-CHEATS-VPN-debug-apk`.
5. Extract the ZIP and install `app-debug.apk` on Android. Android may ask you to allow installation from that source.

## Important status
This is the branded Android app shell, not an active VPN yet. The Connect button deliberately reports that server configuration is missing. No traffic is tunneled and no server credentials are included. To enable real VPN service later, provision servers and integrate the official WireGuard Android tunnel library plus a secure configuration/provisioning flow. Do not ship private WireGuard keys inside a public APK.
