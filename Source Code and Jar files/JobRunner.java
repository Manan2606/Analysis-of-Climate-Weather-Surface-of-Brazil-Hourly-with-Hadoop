import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class JobRunner {

    public static void main(String[] args) throws Exception {
        // Check if sufficient arguments are passed
        if (args.length < 3) {
            System.err.println("Usage: JobRunner <job_type> <input_path> <output_path>");
            System.err.println("job_type: avg_temp | max_wind | total_precipitation");
            System.exit(-1);
        }

        String jobType = args[0];  // Job type (avg_temp, max_wind, total_precipitation)
        String inputPath = args[1];  // Input file path
        String outputPath = args[2];  // Output directory path

        Configuration conf = new Configuration();
        Job job;

        switch (jobType.toLowerCase()) {
            case "avg_temp":
                job = Job.getInstance(conf, "Average Temperature Job");
                job.setJarByClass(AverageTemperatureMapper.class);
                job.setMapperClass(AverageTemperatureMapper.class);
                job.setReducerClass(AverageTemperatureReducer.class);
                job.setOutputKeyClass(Text.class);
                job.setOutputValueClass(FloatWritable.class); // Ensure avg_temp uses FloatWritable
                break;

            case "max_wind":
                job = Job.getInstance(conf, "Maximum Wind Speed Job");
                job.setJarByClass(MaxWindSpeedMapper.class);
                job.setMapperClass(MaxWindSpeedMapper.class);
                job.setReducerClass(MaxWindSpeedReducer.class);
                job.setOutputKeyClass(Text.class);
                job.setOutputValueClass(FloatWritable.class);
                break;

            case "total_precipitation":
                job = Job.getInstance(conf, "Total Precipitation Job");
                job.setJarByClass(TotalPrecipitationMapper.class);
                job.setMapperClass(TotalPrecipitationMapper.class);
                job.setReducerClass(TotalPrecipitationReducer.class);
                job.setOutputKeyClass(Text.class);
                job.setOutputValueClass(FloatWritable.class);
                break;

            default:
                System.err.println("Invalid job type. Use: avg_temp | max_wind | total_precipitation");
                System.exit(-1);
                return;
        }

        // Set input and output paths
        FileInputFormat.addInputPath(job, new Path(inputPath));
        FileOutputFormat.setOutputPath(job, new Path(outputPath));

        // Start the timer
        long startTime = System.currentTimeMillis();

        // Run the job
        boolean jobSuccess = job.waitForCompletion(true);

        // End the timer
        long endTime = System.currentTimeMillis();

        // Calculate and print the execution time
        long executionTime = endTime - startTime;

        if (jobSuccess) {
            System.out.println("Job Type: " + jobType);
            System.out.println("Input Path: " + inputPath);
            System.out.println("Output Path: " + outputPath);
            System.out.println("Execution Time: " + executionTime + " ms");
            System.exit(0);
        } else {
            System.err.println("Job failed.");
            System.exit(1);
        }
    }
}


