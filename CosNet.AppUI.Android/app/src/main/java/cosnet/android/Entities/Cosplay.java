package cosnet.android.Entities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.UUID;

@Entity
public class Cosplay implements Serializable {
   @PrimaryKey(autoGenerate = true)
   public int id;

   @ColumnInfo(name = "cosplay_id")
   @NonNull public String cosplayId;

   @ColumnInfo(name = "cosplay_name")
   @NonNull public String cosplayName;

   @ColumnInfo(name = "cosplay_series")
   @Nullable public String cosplaySeries;

   @ColumnInfo(name = "start_date")
   @Nullable public String startDate;

   @ColumnInfo(name = "due_date")
   @Nullable public String dueDate;

   @Nullable public Double budget;
   @NonNull public String status;

   public Cosplay(){
     this.cosplayId = UUID.randomUUID().toString();
   }
}
