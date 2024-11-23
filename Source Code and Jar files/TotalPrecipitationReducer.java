import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TotalPrecipitationReducer extends Reducer<Text, FloatWritable, Text, FloatWritable> {

    private FloatWritable totalPrecipitation = new FloatWritable();

    @Override
    protected void reduce(Text key, Iterable<FloatWritable> values, Context context) throws IOException, InterruptedException {
        float total = 0;

        // Sum all the precipitation values for this key (station)
        for (FloatWritable value : values) {
            total += value.get();
        }

        totalPrecipitation.set(total);
        context.write(key, totalPrecipitation);
    }
}

