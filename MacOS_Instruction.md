# 📦 **Welcome to the installation guide for QuackyENC!**

## _Please follow these steps to install and run the tool on macOS._

### ✅ **System Requirements:**
- macOS 12.0 Monterey or newer.
- Intel or Apple Silicon (M1/M2) CPU.
- Stable internet connection (for downloading and verifying file if needed).

### 📥 **Step 1: Download the Tool's zip folder**
- Download the latest zip folder from the **[GitHub Releases](https://github.com/duckytran2k6/CLI_QuackyENC/releases)** page.
- Choose the latest version.
- Under the **Assets** section, download the QuackyENC_macOS.zip folder.

### 🖱️ **Step 2: Install the Tool**
1. Unzip the downloaded folder (if it’s compressed).
2. Open the folder and find the **QuackyENC-1.0.0.dmg** file.
3. Double-click to mount it.
4. In the window that appears, drag the QuackyENC icon into the Application folder.
5. Eject the mounted disk image by dragging it to Trash or right-clicking → Eject.

💡 _Note: The first time you run the tool, macOS Gatekeeper may warn you that it's from an unidentified developer. See the Gatekeeper Bypass section below for further set-up._

### 🛠 **First Launch Set-up (Bypass Gatekeeper)**
🔒 Security Notice:
When opening QuackyENC for the first time, macOS may warn:
    “QuackyENC can’t be opened because it is from an unidentified developer.”
This warning is normal for open-source apps not signed with Apple’s paid Developer ID.

✅ QuackyENC is open-source — view the source code at [https://github.com/duckytran2k6/CLI_QuackyENC]
✅ You can verify file integrity using the SHA256 checksum from the release page
✅ QuackyENC does not modify system files or require admin privileges

To run it, please do the following:
1. Open System Settings -> Privacy & Security.
2. Scroll down to the Security section.
3. You'll see a message about QuackyENC being blocked - click "Open Anyway".
4. In the confirmation dialog, click "Open" again.

### ⚙️ **Step 3: Check the tool functionality:**
- After installing the tool, execute these commands:
    1. Open the terminal and change the directory to your tool .app file: cd /Applications/QuackyENC.app/Contents/MacOS
    2. Then run: ./QuackyENC -h
    ^ Once you execute the command, you should see a list of available commands of the tool which can be executed in the order: ./QuackyENC [COMMAND] [OPTION]

💡 If you see a "permission denied" error, make it executable: chmod +x QuackyENC

### ⚙️ **Optional: Add QuackyENC to the system PATH**
If you want to run the tool from any Terminal window without changing directories, please execute the following command in your Terminal:
    - For zsh (default on macOS Catalina and later):
        echo 'export PATH="/Applications/QuackyENC.app/Contents/MacOS:$PATH"' >> ~/.zshrc
        source ~/.zshrc
    - For bash:
        echo 'export PATH="/Applications/QuackyENC.app/Contents/MacOS:$PATH"' >> ~/.bash_profile
        source ~/.bash_profile

After that, you can run: QuackyENC [COMMAND] [OPTION] from any Terminal window you open.
