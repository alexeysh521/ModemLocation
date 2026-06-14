# ModemLocation - Система мониторинга локации модемов

Бэкенд-приложение на Spring Boot + PostgreSQL, упакованное в Docker. Автоматически принимает метрики и подтягивает геокоординаты через OpenCellID API.

## Как запустить проект

Для запуска вам понадобятся только установленный **Git** и **Docker Desktop**.

1. **Клонируйте репозиторий:**
   ```bash
   git clone https://github.com/alexeysh521/ModemLocation.git
   cd ModemLocation
   ```
2. **В корне проекта у файла `.env.erase` удалить `.erase`, вместо `YOU_TOKEN_OpenCell` добавить свой токен, чтобы его получить зарегистируйтесь на `OpenCelliD`. Порт `8080 (по умолчанию)`, вы можете изменить на любой**
3. **Запустите проект одной командой**
   ```bash
   docker compose up -d --build
   ```
   
