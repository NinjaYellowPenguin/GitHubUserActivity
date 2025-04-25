<h1>GitHub User Activity </h1> <p class="text-sm text-balance text-gray-500"> Use GitHub API to fetch user activity and display it in the terminal. </p> </div>  </div> </div> <astro-island uid="1mDlS4" prefix="r12" component-url="/_astro/ProjectStepper.DVJDYZDp.js" component-export="ProjectStepper" renderer-url="/_astro/client.DNdMcqFj.js" props="{&quot;projectId&quot;:[0,&quot;github-user-activity&quot;]}" client="load" opts="{&quot;name&quot;:&quot;ProjectStepper&quot;,&quot;value&quot;:true}" await-children=""><div class="relative top-0 -mx-4 my-5 overflow-hidden rounded-none border border-x-0 bg-white transition-all sm:sticky sm:mx-0 sm:rounded-lg sm:border-x"></div></astro-island> <div class="prose prose-h2:mb-3 prose-h2:mt-5 prose-h3:mb-1 prose-h3:mt-5 prose-p:mb-2 prose-blockquote:font-normal prose-blockquote:text-gray-500 prose-pre:my-3 prose-ul:my-3.5 prose-hr:my-5 max-w-full [&amp;>ul>li]:my-1"> <p>In this project, you will build a simple command line interface (CLI) to fetch the recent activity of a GitHub user and display it in the terminal. This project will help you practice your programming skills, including working with APIs, handling JSON data, and building a simple CLI application.</p>
<h2 id="requirements">Requirements</h2>
<p>The application should run from the command line, accept the GitHub username as an argument, fetch the user’s recent activity using the GitHub API, and display it in the terminal. The user should be able to:</p>
<ul>
<li>Provide the GitHub username as an argument when running the CLI.
<pre class="astro-code dracula" style="background-color:#282A36;color:#F8F8F2; overflow-x: auto;" tabindex="0" data-language="bash"><code><span class="line"><span style="color:#50FA7B">github-activity</span><span style="color:#FF79C6"> &lt;</span><span style="color:#F1FA8C">usernam</span><span style="color:#F8F8F2">e</span><span style="color:#FF79C6">&gt;</span></span></code></pre>
</li>
<li>Fetch the recent activity of the specified GitHub user using the GitHub API. You can use the following endpoint to fetch the user’s activity:
<pre class="astro-code dracula" style="background-color:#282A36;color:#F8F8F2; overflow-x: auto;" tabindex="0" data-language="plaintext"><code><span class="line"><span># https://api.github.com/users/&lt;username&gt;/events</span></span>
<span class="line"><span># Example: https://api.github.com/users/kamranahmedse/events</span></span></code></pre>
</li>
<li>Display the fetched activity in the terminal.
<pre class="astro-code dracula" style="background-color:#282A36;color:#F8F8F2; overflow-x: auto;" tabindex="0" data-language="plaintext"><code><span class="line"><span>Output:</span></span>
<span class="line"><span>- Pushed 3 commits to kamranahmedse/developer-roadmap</span></span>
<span class="line"><span>- Opened a new issue in kamranahmedse/developer-roadmap</span></span>
<span class="line"><span>- Starred kamranahmedse/developer-roadmap</span></span>
<span class="line"><span>- ...</span></span></code></pre>
You can <a href="https://docs.github.com/en/rest/activity/events?apiVersion=2022-11-28" rel="noopener noreferrer nofollow" target="_blank">learn more about the GitHub API here</a>.</li>
<li>Handle errors gracefully, such as invalid usernames or API failures.</li>
<li>Use a programming language of your choice to build this project.</li>
<li>Do not use any external libraries or frameworks to fetch the GitHub activity.</li>
</ul>
<hr>
<p>If you are looking to build a more advanced version of this project, you can consider adding features like filtering the activity by event type, displaying the activity in a more structured format, or caching the fetched data to improve performance. You can also explore other endpoints of the GitHub API to fetch additional information about the user or their repositories.</p> </div> <div class="mt-5 flex flex-wrap items-center justify-center rounded-lg p-2.5 text-sm"> <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 98 96" xmlns:v="https://vecta.io/nano" class="mr-2 inline-block h-5 w-5"><path fill-rule="evenodd" d="M48.854 0C21.839 0 0 22 0 49.217c0 21.756 13.993 40.172 33.405 46.69 2.427.49 3.316-1.059 3.316-2.362l-.08-9.127c-13.59 2.934-16.42-5.867-16.42-5.867-2.184-5.704-5.42-7.17-5.42-7.17-4.448-3.015.324-3.015.324-3.015 4.934.326 7.523 5.052 7.523 5.052 4.367 7.496 11.404 5.378 14.235 4.074.404-3.178 1.699-5.378 3.074-6.6-10.839-1.141-22.243-5.378-22.243-24.283 0-5.378 1.94-9.778 5.014-13.2-.485-1.222-2.184-6.275.486-13.038 0 0 4.125-1.304 13.426 5.052a46.97 46.97 0 0 1 12.214-1.63c4.125 0 8.33.571 12.213 1.63 9.302-6.356 13.427-5.052 13.427-5.052 2.67 6.763.97 11.816.485 13.038 3.155 3.422 5.015 7.822 5.015 13.2 0 18.905-11.404 23.06-22.324 24.283 1.78 1.548 3.316 4.481 3.316 9.126l-.08 13.526c0 1.304.89 2.853 3.316 2.364 19.412-6.52 33.405-24.935 33.405-46.691C97.707 22 75.788 0 48.854 0z" fill="currentColor"></path></svg>
Found a mistake?
<a class="ml-1 underline underline-offset-2" href="https://github.com/kamranahmedse/developer-roadmap/tree/master/src/data/projects/github-user-activity.md" target="_blank">
Help us improve.
</a> </div> </div>
