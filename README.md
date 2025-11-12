## Demo Web Flux

## Run
```
make compose
```

## Testing
---

### 🧾 1️⃣ GET all products (expect empty array initially)

```bash
curl -X GET http://localhost:8080/api/products \
  -H "Accept: application/json"
```

---

### 💾 2️⃣ POST create a new product

```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Laptop Test",
    "price": 1500.0
  }'
```

> 💡 Catatan: Simpan `id` dari respons JSON hasil POST ini — akan digunakan di langkah berikutnya.

---

### 🔍 3️⃣ GET product by ID

Ganti `{id}` dengan ID sebenarnya dari hasil POST di atas.

```bash
curl -X GET http://localhost:8080/api/products/{id} \
  -H "Accept: application/json"
```

Contoh:

```bash
curl -X GET http://localhost:8080/api/products/6913f52e6b7648a3c246b953 \
  -H "Accept: application/json"
```

---

### 📋 4️⃣ GET all products again (should show the created one)

```bash
curl -X GET http://localhost:8080/api/products \
  -H "Accept: application/json"
```

---

### ❌ 5️⃣ DELETE product by ID

Ganti `{id}` dengan ID yang sama seperti di atas.

```bash
curl -X DELETE http://localhost:8080/api/products/{id} \
  -H "Accept: application/json"
```

Contoh:

```bash
curl -X DELETE http://localhost:8080/api/products/6913f52e6b7648a3c246b953 \
  -H "Accept: application/json"
```

---

### 🔁 6️⃣ GET all products after delete (should be empty again)

```bash
curl -X GET http://localhost:8080/api/products \
  -H "Accept: application/json"
```
