# 📦 **Welcome to the installation guide for QuackyENC!**

## _Please follow these steps to install and run the tool on Linux._

### 🔒 **Security Notice:**
* 🔓 **Open Source** — QuackyENC is fully open-source, you can view the source code at [https://github.com/duckytran2k6/CLI_QuackyENC].
* 🛡️ **No Extra Privileges Needed** — Running QuackyENC does not require root or administrative privileges.
* 🔑 **Verify File Integrity** — Check your downloaded file against the published SHA-256 checksum and run the following command:
  - `sha256sum thefilename.deb`.
  - The command will output a hash code which can be used to compare.
  - If the hash code from the output does not matched with the Sha-256 checksums, please visit [SECURITY](SECURITY.md) page for more information.

### ✅ **System Requirements:**
- Ubuntu/Debian or any Debian-based distribution.
- Stable internet connection (for downloading and verifying file if needed).

### 📥 **Step 1: Download the Tool's zip folder**
- Download the latest zip folder from the **[GitHub Releases](https://github.com/duckytran2k6/CLI_QuackyENC/releases)** page.
- Choose the latest version.
- Under the **Assets** section, download the **.deb** file.

### 🖱️ **Step 2: Install the Tool**
Perform these following commands:
1. Locate the .deb file: QuackyENC-1.0.0.deb
2. Install the tool: sudo dpkg -i QuackyENC-1.0.0.deb
3. Fix any missing dependencies of the tool (recommended): sudo apt-get install -f

### ⚙️ **Step 3: Check the tool functionality:**
- After installing the tool, execute these commands to verify the installation: 
   1. QuackyENC -h -> This will show you every available commands of the tool.
   2. Once you execute the command, you should see a list of available commands of the tool which can be executed in the order: QuackyENC [COMMAND] [OPTION]

     