import com.google.gson.*;
import com.google.gson.annotations.*;
import com.google.gson.internal.*;
import com.google.gson.internal.*;
import com.google.gson.internal.bind.*;
import com.google.gson.reflect.*;
import com.google.gson.stream.*;

import java.io.*;
class MyTypeAdapter<T> extends TypeAdapter<T> {
    public T read(JsonReader reader) throws IOException {
        return null;
    }

    public void write(JsonWriter writer, T obj) throws IOException {
        if (obj == null) {
            writer.nullValue();
            return;
        }
        writer.value(obj.toString());
    }
}