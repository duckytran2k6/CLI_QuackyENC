# 📦 **Welcome to the installation guide for QuackyENC!**

## _Please follow these steps to install and run the tool on Windows._

### 🔒 **Security Notice:**
* ✅ QuackyENC is open-source — view the source code at [https://github.com/duckytran2k6/CLI_QuackyENC].
* ✅ You can verify file integrity using the Sha-256 checksums from the release page and run the following command:
  - Command Prompt: `CertUtil -hashfile QuackyENC-1.1.0.exe SHA256`.
  - Powershell: `Get-FileHash QuackyENC-1.1.0.exe -Algorithm SHA256`.
  - The command will output a hash code which can be used to compare.
  - If the hash code from the output does not matched with the Sha-256 checksums, please visit [SECURITY](SECURITY.md) page for more information.

### ✅ **System Requirements:**
- Windows 10 or newer (64-bit).
- Stable internet connection (for downloading and verifying file if needed).

### 📥 **Step 1: Download the Tool's zip folder**
- Download the latest zip folder from the **[GitHub Releases](https://github.com/duckytran2k6/CLI_QuackyENC/releases)** page.
- Choose the latest version.
- Under the **Assets** section, download the **QuackyENC-1.0.0.exe** file.

### 🖱️ **Step 2: Install the Tool**
1. Open the folder explorer and find the **QuackyENC-1.0.0.exe** file.
2. Double-click to install it.
3. If Windows SmartScreen appears, select "Run Anyway".
4. Once installed, check your "Program Files" folder in C drive, and you should see a folder named "QuackyENC" with an .exe file in it.

### ⚙️ **Step 3: Check the tool functionality:**
- After installing the tool, execute these commands:
    1. Open the terminal and change the directory to your tool .exe file: cd C:/Program Files/QuackyENC
    2. If you are using **Command Prompts**: QuackyENC.exe -h
    3. If you are using **PowerShell**: .\QuackyENC.exe -h
    4. Once you execute the command, you should see a list of available commands of the tool which can be executed in the order:
[COMMAND] [OPTION]

### ⚙️ **Optional: Add QuackyENC to the system PATH**
If you want to run the tool from any Terminal window without changing directories, please execute the following command in your Terminal:
1. Open **Start** -> **Search** -> **Environment Variables**.
2. Select **Edit the system environment variables**.
3. Click **Environment Variables...**
4. Locate inside the **User Variables** section, select **Path**.
5. Click **Edit** -> **New** and add: C:/Program Files/QuackyENC
6. Click **OK** and restart your terminal.

After that, you can run: QuackyENC [COMMAND] [OPTION] from any Terminal window you open.
        