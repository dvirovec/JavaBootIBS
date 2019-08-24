
<template>
<div>
		<div class="ui modal">
				<div class="header">
						{{ questionText }}
				</div>
				<div class="image content">
					<div class="description">
						{{ questionText }}
					</div>
				</div>
				<div class="actions">
					<div class="ui black deny button">
						No
					</div>
					<div class="ui red positive button">
						Yes
					</div>
				</div>
			</div>
</div>			
</template>

<script>
	export default {
		name:"AskQuestion",
		props: {
			questionText: { type: string }
		}
	}
</script>