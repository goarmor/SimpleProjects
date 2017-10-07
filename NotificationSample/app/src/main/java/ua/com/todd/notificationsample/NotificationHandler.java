package ua.com.todd.notificationsample;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.widget.Toast;

import java.util.Random;

public class NotificationHandler {
	private static NotificationHandler nHandler;
	private static NotificationManager mNotificationManager;


	private NotificationHandler () {}



	public static  NotificationHandler getInstance(Context context) {
		if(nHandler == null) {
			nHandler = new NotificationHandler();
			mNotificationManager =
					(NotificationManager) context.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
		}

		return nHandler;
	}



	public void createSimpleNotification(Context context) {
		Intent resultIntent = new Intent(context, TestActivity.class);

		TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
		stackBuilder.addParentStack(TestActivity.class);
		stackBuilder.addNextIntent(resultIntent);

		PendingIntent resultPending = stackBuilder
				.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
				.setSmallIcon(R.drawable.ic_launcher) // notification icon
				.setContentTitle("I'm a simple notification") // main title of the notification
				.setContentText("I'm the text of the simple notification") // notification text
				.setContentIntent(resultPending); // notification intent

		mNotificationManager.notify(10, mBuilder.build());
	}


	public void createExpandableNotification (Context context) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
			String lorem = context.getResources().getString(R.string.long_lorem);
			String [] content = lorem.split("\\.");

			inboxStyle.setBigContentTitle("This is a big title");
			for (String line : content) {
				inboxStyle.addLine(line);
			}

			NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(context)
					.setSmallIcon(R.drawable.ic_launcher) // notification icon
					.setContentTitle("Expandable notification") // title of notification
					.setContentText("This is an example of an expandable notification") // text inside the notification
					.setStyle(inboxStyle); // adds the expandable content to the notification

			mNotificationManager.notify(11, nBuilder.build());

		} else {
			Toast.makeText(context, "Can't show", Toast.LENGTH_LONG).show();
		}
	}



	public void createProgressNotification (final Context context) {

		final int progresID = new Random().nextInt(1000);

		final NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(context)
				.setSmallIcon(R.drawable.refresh)
				.setContentTitle("Progres notification")
				.setContentText("Now waiting")
				.setTicker("Progress notification created")
				.setUsesChronometer(true)
				.setProgress(100, 0, true);



		AsyncTask<Integer, Integer, Integer> downloadTask = new AsyncTask<Integer, Integer, Integer>() {
			@Override
			protected void onPreExecute () {
				super.onPreExecute();
				mNotificationManager.notify(progresID, nBuilder.build());
			}

			@Override
			protected Integer doInBackground (Integer... params) {
				try {
					Thread.sleep(5000);

					for (int i = 0; i < 101; i+=5) {
						nBuilder
							.setContentTitle("Progress running...")
							.setContentText("Now running...")
							.setProgress(100, i, false)
							.setSmallIcon(android.R.drawable.stat_sys_download)
							.setContentInfo(i + " %");

						mNotificationManager.notify(progresID, nBuilder.build());
						Thread.sleep(500);
					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				return null;
			}


			@Override
			protected void onPostExecute (Integer integer) {
				super.onPostExecute(integer);

				nBuilder.setContentText("Progress finished :D")
						.setContentTitle("Progress finished !!")
						.setTicker("Progress finished !!!")
						.setSmallIcon(R.drawable.accept)
						.setUsesChronometer(false);

				mNotificationManager.notify(progresID, nBuilder.build());
			}
		};

		downloadTask.execute();
	}


	public void createButtonNotification (Context context) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			Intent intent = new Intent(context, TestActivity.class);
			PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);

			NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(context)
					.setSmallIcon(R.drawable.ic_launcher)
					.setContentTitle("Button notification")
					.setContentText("Expand to show the buttons...")
					.setTicker("Showing button notification")
					.addAction(R.drawable.accept, "Accept", pIntent)
					.addAction(R.drawable.cancel, "Cancel", pIntent);

			mNotificationManager.notify(1001, nBuilder.build());

		} else {
			Toast.makeText(context, "You need a higher version", Toast.LENGTH_LONG).show();
		}
	}
}
