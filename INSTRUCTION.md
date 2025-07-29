## ✅ **PREREQUISITES:**
- Make sure to have Java JDK downloaded. (Run -info/--information for the download link)
- The directory to the tool's extracted zip folder. (i.e ../Download/BetaRelease/BetaRelease)
which can be obtained from the file explorer at the top section.

## 🖥️ **INSTRUCTIONS:**
- Once you have successfully download the zip folder, please open your OS terminal and  perform the following command:
  + cd (the directory as stated above)
- Then, use the following command order: java -jar duckytool.jar (choose the command for the service you want to perform)
- For Encryption/Decryption choose between the 2 methods: -p for password method and -kp for key pair method.
    * For Key pair method, the receiver must use the key pair generator before proceeding using -kpg command.
    * Refers to the **_NOTES_** section below for more information on each method.

## 📂 **ENCRYPTION:**
- Choose the desire method and start with uploading the desire files to be encrypted (1-20 files).
- Then, enter the required information:
    + Enter the desire password for the password method.
    + Upload the receiver's public key for the key pair method.
 
- Once the encryption process has been finished, the sender will receive a zip file containing the encrypted files and the following:
    + _For password method:_ Encrypted text, IV, and salt files.
    + _For key pair method:_ Encrypted text and secret aes key + IV files.
  
- Finally, the sender can either send the zip file to the receiver or extract and send the files separately!

## 📂 **DECRYPTION:**
- For this, the receiver will need to choose the appropriate method accordingly to the one the sender has chosen for the encryption process.
- Then, the receiver needs to upload the files received from the sender (make sure to include in the required info files as listed above!).
- Finally, the receiver will receive a zip file containing all the decrypted text files.

## ❗ **NOTES:**
- _For password method, please make sure to enter a valid password containing these requirements:_
    1. The minimum length must be 8 characters.
    2. Must include at least 1 lower case letter.
    3. Must include at least 1 upper case letter.
    4. Must include at least 1 number (from 0-9).
    5. Must include at least 1 special character.

- _For key pair method:_ The receiver **MUST** keep the private key file securely and **ONLY** share the public key file to the sender.