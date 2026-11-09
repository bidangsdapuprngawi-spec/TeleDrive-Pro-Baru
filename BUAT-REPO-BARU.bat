@echo off
echo Buat repo BARU dulu di github.com/new dengan nama TeleDrive-Pro-Baru
pause
cd /d %~dp0
 git init
 git add .
 git commit -m "TeleDrive Pro baru dari awal"
 git branch -M main
 git remote add origin https://github.com/bidangsdapuprngawi-spec/TeleDrive-Pro-Baru.git
 git push -u origin main
pause
