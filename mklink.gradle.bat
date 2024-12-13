@echo off
@echo Create a junction to a common .gradle directory.
for %%i in (".gradle") do set attribs=%%~ai
if "%attribs:~8,1%" == "l" (
	@echo It is already a junction.
) else (
	rd /s /q .gradle
	mklink /j .gradle ..\..\.gradle
	@echo Done.
)
