# RSA

# RSA Encryption and Decryption in Java

## Overview

This project implements the **RSA algorithm** for encryption and decryption in Java. It demonstrates key concepts such as key generation, encryption, and decryption, while incorporating important **object-oriented programming principles** like **inheritance, polymorphism, method overriding, and generics**.

## Features

- Generate **public and private keys** using the RSA algorithm.
- Encrypt and decrypt messages securely.
- Demonstrates Java concepts such as:
  - **Inheritance & Polymorphism** (Base `CipherAlgorithm` class with `RSACipher` subclass)
  - **Method Overriding** (Custom implementations of `encrypt()` and `decrypt()`)
  - **Generics** (Using generic collections for storing encrypted messages)
  - **Interfaces** (`Encryptor` interface for encryption and decryption)

## Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/awaisAhmed19/RSA.git
   cd RSA
   ```
2. Compile the Java files:
   ```sh
   javac src/Main.java
   ```
3. Run the application:
   ```sh
   java src.Main
   ```

## Usage

### **1. Generate RSA Keys**

The program generates a public/private key pair using a key size of **2048 bits**.

### **2. Encrypt a Message**

A message can be encrypted using the generated **public key**, converting plain text into ciphertext.

### **3. Decrypt a Message**

The encrypted message is decrypted using the **private key**, restoring the original text.

## Code Structure

```
RSA/
│── src/
|   ├── Main.java
│   ├── RSAEncryptor.java
|   ├── RSADecryptor.java
│   ├── RSAKeyGenerator.java
|   ├── RSATest.java
│── Utils/
│   ├── Utils.java
│   ├── BaseConvertor.java
│-- README.md                 # Project documentation
│-- LICENSE                   # License information
```

## Example Output

```
Generated Public Key: (e, n)
Generated Private Key: (d, n)
Original Message: "Hello, RSA!"
Encrypted Message: "... (ciphertext) ..."
Decrypted Message: "Hello, RSA!"
```

## Future Improvements

- Support for **larger key sizes** (4096 bits for enhanced security).
- Implementation of **hybrid encryption** (AES + RSA for performance).
- GUI-based interface for easy encryption/decryption.

## Contributing

Pull requests are welcome! If you have suggestions for improving the project, feel free to fork the repo and submit a PR.

---

Made with ❤️ by [Awais Ahmed]
