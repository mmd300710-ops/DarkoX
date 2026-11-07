# Darko X Android Browser — প্রাথমিক সম্পূর্ণ প্রজেক্ট

এই ZIP-এ Android WebView-ভিত্তিক একটি buildable starter browser, আপনার দেওয়া একই Skull-X লোগো, DKX rewards preview, admin setup placeholder, Base network configuration এবং GitHub Actions workflow আছে।

## ১) GitHub-এ আপলোড ও APK build
1. ZIP extract করুন।
2. সব ফাইল GitHub repository-র root-এ upload করুন।
3. GitHub-এর **Actions** tab খুলুন।
4. `Build Darko X Android APK` workflow run/সম্পন্ন হওয়া পর্যন্ত অপেক্ষা করুন।
5. workflow-এর Artifacts থেকে `DarkoX-debug-apk` download করুন।
6. APK install করার আগে নিজের test device-এ পরীক্ষা করুন।

লোকাল build: Android Studio/Gradle এবং JDK 17 দিয়ে `gradle assembleDebug` চালান।

## ২) এখন কী কাজ করে
- Android WebView browser shell
- URL bar: website address বা Google search
- Back, Home, DKX preview এবং Admin setup screens
- একই আপলোড করা লোগো browser icon ও app screens-এ
- Base Mainnet chain ID 8453 ও ERC-20 placeholder
- GitHub Actions দিয়ে debug APK build

## ৩) এখনো কী চালু নয়
**DKX token deploy করা হয়নি। Wallet/private-key system নেই। Rewards/withdrawals/exchange integration নেই।** DKX balance ইচ্ছামতো বাড়ে না; rewards preview-তে 0.00 দেখায়। এটি ইচ্ছাকৃত—সার্ভার-সাইড যাচাই ছাড়া বাস্তব টোকেন দেওয়া বিপজ্জনক এবং সহজে জালিয়াতি হতে পারে।

## ৪) পরবর্তীতে কোথায় তথ্য বসাবেন
- `config/token.example.json`: token name, symbol, Base chain ID এবং contract address।
- `app/src/main/assets/rewards.html`: ব্যবহারকারীর জন্য network/contract status text।
- `app/src/main/assets/admin.html`: admin setup checklist (এটি production admin panel নয়)।
- `backend/.env.example`: backend environment variable-এর নাম।
- `backend/server.js`: API starter; rewards endpoint ইচ্ছাকৃতভাবে disabled।

Contract address deploy ও verify করার পরেই প্রকৃত address বসাবেন। Token deploy না করে placeholder address ব্যবহার করবেন না।

## ৫) Production-এ যাওয়ার আগে অবশ্যই
- ERC-20 contract-এর source verify, independent audit এবং tokenomics review।
- Secure backend, database, MFA-protected admin panel, role-based access, audit logs।
- Ad provider-এর server-to-server callbacks, anti-bot/fraud controls এবং reward caps।
- WalletConnect বা audited wallet provider; কখনো seed phrase/private key নিজের app/server-এ সংগ্রহ করবেন না।
- HTTPS, privacy policy, terms, consent, data deletion এবং Google Play policy review।
- Release APK/AAB signing key নিরাপদে সংরক্ষণ; GitHub-এ keystore বা passwords commit করবেন না।

## গুরুত্বপূর্ণ সীমাবদ্ধতা
এটি একটি starter project, production-ready crypto wallet বা পূর্ণাঙ্গ বিজ্ঞাপন-ভিত্তিক reward economy নয়। Google Play-এ প্রকাশ, বিজ্ঞাপন থেকে আয় ভাগ, crypto rewards এবং exchange listing-এর জন্য প্রযোজ্য আইন, বিজ্ঞাপন নীতি ও Play policies আলাদাভাবে যাচাই করুন।
