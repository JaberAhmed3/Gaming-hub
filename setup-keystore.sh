#!/bin/bash

# Keystore Generation Script for Gaming Hub
# Run this on your local machine with Java installed

set -e

echo "=========================================="
echo "Gaming Hub Keystore Setup"
echo "=========================================="

# Configuration
KEYSTORE_FILE="android/upload-keystore.jks"
ALIAS="upload"
KEYSTORE_PASSWORD="gaming_hub_2024"
KEY_PASSWORD="gaming_hub_2024"
VALIDITY_DAYS=10000

# Check if keytool is available
if ! command -v keytool &> /dev/null; then
    echo "❌ Error: keytool not found. Please install Java JDK."
    echo "   On macOS: brew install openjdk"
    echo "   On Linux: sudo apt-get install default-jdk"
    exit 1
fi

# Generate keystore
echo "Generating keystore..."
keytool -genkey -v \
    -keystore "$KEYSTORE_FILE" \
    -keyalg RSA \
    -keysize 2048 \
    -validity $VALIDITY_DAYS \
    -alias "$ALIAS" \
    -dname "CN=Gaming Hub,OU=Development,O=JaberAhmed3,L=Bangladesh,S=Dhaka,C=BD" \
    -storepass "$KEYSTORE_PASSWORD" \
    -keypass "$KEY_PASSWORD"

echo "✅ Keystore created: $KEYSTORE_FILE"

# Encode to base64
echo ""
echo "Encoding keystore to base64..."
base64 "$KEYSTORE_FILE" > keystore.txt

echo "✅ Base64 encoded: keystore.txt"

echo ""
echo "=========================================="
echo "GitHub Secrets to Add"
echo "=========================================="
echo ""
echo "Go to: https://github.com/JaberAhmed3/Gaming-hub/settings/secrets/actions"
echo ""
echo "Add these secrets:"
echo ""
echo "1. ENCODED_KEYSTORE"
echo "   Value: (copy contents of keystore.txt)"
echo ""
echo "2. KEY_ALIAS"
echo "   Value: $ALIAS"
echo ""
echo "3. KEYSTORE_PASSWORD"
echo "   Value: $KEYSTORE_PASSWORD"
echo ""
echo "4. KEY_PASSWORD"
echo "   Value: $KEY_PASSWORD"
echo ""
echo "=========================================="
echo ""
echo "⚠️  Keep upload-keystore.jks safe!"
echo "   Don't commit it to GitHub"
echo ""
